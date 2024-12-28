package org.example.rfshop.Auth.Application.LoginUseCase;

import org.example.rfshop.Auth.Domain.Dto.Response.LoginResponseDto;
import org.example.rfshop.Auth.Infrastructure.Security.Jwt.TokenProvider;
import org.example.rfshop.Auth.Domain.Dto.Request.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginUseCaseImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponseDto execute(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            return LoginResponseDto.builder().token(token).createdAt(new Date()).build();
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials", e);
        } catch (Exception e) {
            throw new RuntimeException("Login failed", e);
        }
    }
}