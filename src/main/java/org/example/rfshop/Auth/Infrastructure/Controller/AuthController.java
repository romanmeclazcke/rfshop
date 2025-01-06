package org.example.rfshop.Auth.Infrastructure.Controller;

import org.example.rfshop.Auth.Application.ChangePassword.ChangePasswordUseCase;
import org.example.rfshop.Auth.Application.LoginUseCase.LoginUseCase;
import org.example.rfshop.Auth.Domain.Dto.Request.ChangePasswordDto;
import org.example.rfshop.Auth.Domain.Dto.Request.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @Autowired
    public AuthController(LoginUseCase loginUseCase, ChangePasswordUseCase changePasswordUseCase) {
        this.loginUseCase = loginUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>( loginUseCase.execute(loginDto), HttpStatus.OK);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        this.changePasswordUseCase.execute(changePasswordDto);
        return new ResponseEntity<>(  "Password update successfully",HttpStatus.OK);
    }
}
