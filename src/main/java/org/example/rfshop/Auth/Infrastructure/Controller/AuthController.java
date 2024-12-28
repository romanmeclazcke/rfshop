package org.example.rfshop.Auth.Infrastructure.Controller;

import org.example.rfshop.Auth.Application.LoginUseCase.LoginUseCase;
import org.example.rfshop.Auth.Domain.Dto.Request.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    @Autowired
    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>( loginUseCase.execute(loginDto), HttpStatus.OK);
    }
}
