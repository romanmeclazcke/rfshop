package org.example.rfshop.User.Application.CreateUserUseCase;

import org.example.rfshop.User.Infrastructure.Exception.EmailAlreadyInUse;
import org.example.rfshop.User.Infrastructure.Mapper.UserMapper;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        this.userRepository.findUserByEmail(createUserDto.getEmail()).ifPresent(user -> {throw new EmailAlreadyInUse("Email already in user :" + user.getEmail());});
        User user = userMapper.toEntity(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

}
