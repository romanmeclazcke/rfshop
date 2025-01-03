package org.example.rfshop.User.Application.UpdateUserUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.User.Infrastructure.Mapper.UserMapper;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.example.rfshop.User.domain.Dto.Request.UpdateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserUseCaseImpl  implements UpdateUserUseCase{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UpdateUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto execute(Long userId, UpdateUserDto updateUserDto) {
        User user  = this.userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User with id "+ userId + " not found"));

        Optional.ofNullable(updateUserDto.getName()).ifPresent(user::setName);
        Optional.ofNullable(updateUserDto.getLastName()).ifPresent(user::setLastName);

        return this.userMapper.toDto(userRepository.save(user));
    }
}
