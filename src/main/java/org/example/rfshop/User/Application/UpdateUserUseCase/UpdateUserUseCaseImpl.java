package org.example.rfshop.User.Application.UpdateUserUseCase;

import org.example.rfshop.User.domain.Dto.Request.UpdateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCaseImpl  implements UpdateUserUseCase{

    @Override
    public UserResponseDto updateUser(Long userId, UpdateUserDto updateUserDto) {
        return null;
    }
}
