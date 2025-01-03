package org.example.rfshop.Auth.Application.LoginUseCase;

import org.example.rfshop.Auth.Domain.Dto.Request.LoginDto;
import org.example.rfshop.Auth.Domain.Dto.Response.LoginResponseDto;

public interface LoginUseCase {
    LoginResponseDto execute(LoginDto loginDto);
}
