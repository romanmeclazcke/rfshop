package org.example.rfshop.User.Application.CreateUserUseCase;

import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.springframework.stereotype.Service;


@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase{
    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        System.out.println("Hello");
        return null;
    }
}
