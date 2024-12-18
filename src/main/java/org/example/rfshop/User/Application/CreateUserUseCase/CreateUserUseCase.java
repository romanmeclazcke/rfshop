package org.example.rfshop.User.Application.CreateUserUseCase;

import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;

public interface CreateUserUseCase {
    UserResponseDto execute(CreateUserDto createUserDto);
}
