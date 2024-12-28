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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private static final Logger logger = LoggerFactory.getLogger(LoginUseCaseImpl.class);

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginUseCaseImpl(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponseDto execute(LoginDto loginDto) {
        System.out.println(loginDto);
        try {
            logger.info("Attempting to authenticate user with email: {}", loginDto.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            logger.info("Authentication successful for user with email: {}", loginDto.getEmail());
            return LoginResponseDto.builder().token(token).createdAt(new Date()).build();
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user with email: {}", loginDto.getEmail());
            throw new RuntimeException("Invalid credentials", e);
        } catch (Exception e) {
            logger.error("Error during login for user with email: {}", loginDto.getEmail(), e);
            throw new RuntimeException("Login failed", e);
        }
    }
}