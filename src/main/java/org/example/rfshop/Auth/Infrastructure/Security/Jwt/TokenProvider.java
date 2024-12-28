package org.example.rfshop.Auth.Infrastructure.Security.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    @Value("${JWT_SECRET}")
    private String secret;
    private static final String AUTHORITIES_KEY = "auth";
    private SecretKey key;
    private JwtParser jwtParser;
    private final int tokenValidityInMilliseconds;

    public TokenProvider() {
        System.out.println(this.secret);
        this.tokenValidityInMilliseconds = 1000 * 86400; // Token  por 1 día.
    }

    //this will be executed when all dependencies are injected, i assure that the secret is injected
    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key)
                .setExpiration(validity)
                .setIssuedAt(new Date())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            System.out.println("Token: " + token);
            final var claims = jwtParser.parseClaimsJws(token);
            this.checkTokenExpiration(claims);
            return true;
        } catch (JwtException e) {
            throw new JwtException("Invalid Token", e);
        }
    }

    private void checkTokenExpiration(Jws<Claims> token) {
        try {
            final var payload = token.getBody();
            if (payload.getExpiration().before(new Date()) || payload.getIssuedAt().after(new Date((new Date()).getTime() + this.tokenValidityInMilliseconds))) {
                throw new ExpiredJwtException(null, null, null);
            }
        } catch (Exception e) {
            throw new ExpiredJwtException(null, null, null);
        }
    }
}