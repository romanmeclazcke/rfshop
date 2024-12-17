package org.example.rfshop.User.Application.CreateUserUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.User.Infrastructure.Exception.EmailAlreadyInUse;
import org.example.rfshop.User.Infrastructure.Exception.RolNotFound;
import org.example.rfshop.User.Infrastructure.Mapper.UserMapper;
import org.example.rfshop.User.Infrastructure.Model.Role;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    @Autowired
    public CreateUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepositorys) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepositorys;
    }

    @Override
    public UserResponseDto execute(CreateUserDto createUserDto) {
        this.userRepository.findUserByEmail(createUserDto.getEmail()).ifPresent(user -> {throw new EmailAlreadyInUse("Email already in user :" + user.getEmail());});
        User user = userMapper.toEntity(createUserDto);
        Role role  = this.roleRepository.findById(createUserDto.getRolId()).orElseThrow(()-> new RolNotFound("Rol with id: "+ createUserDto.getRolId() +" not found"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

}
