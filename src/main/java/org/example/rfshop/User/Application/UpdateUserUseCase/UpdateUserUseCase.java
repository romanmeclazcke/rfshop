package org.example.rfshop.User.Application.UpdateUserUseCase;

import org.example.rfshop.User.domain.Dto.Request.UpdateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;

public interface UpdateUserUseCase {
    UserResponseDto updateUser(Long userId, UpdateUserDto updateUserDto);
}
