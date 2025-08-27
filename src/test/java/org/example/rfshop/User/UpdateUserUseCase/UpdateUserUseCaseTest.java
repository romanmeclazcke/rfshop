package org.example.rfshop.User.UpdateUserUseCase;

import org.example.rfshop.User.Application.UpdateUserUseCase.UpdateUserUseCaseImpl;
import org.example.rfshop.User.Infrastructure.Mapper.UserMapper;
import org.example.rfshop.User.Infrastructure.Model.Role;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.example.rfshop.User.domain.Dto.Request.UpdateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    private static User user;
    private static UpdateUserDto updateUserDto;
    private static UserResponseDto userResponseDto;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1L)
                .email("usertest2@gmail.com")
                .name("test")
                .lastName("test")
                .password("test")
                .role(new Role(1L, "USER"))
                .build();

        updateUserDto = UpdateUserDto.builder().
                name("nameChanged")
                .build();

        userResponseDto = UserResponseDto.builder()
                .id(1L)
                .email("usertest2@gmail.com")
                .name("nameChanged")
                .lastName("test")
                .password("test")
                .role(new Role(1L, "USER"))
                .build();
    }

    @Test
    public void updateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userMapper.toDto(user)).thenReturn(userResponseDto);
        when(userRepository.save(user)).thenReturn(user);

        UserResponseDto response = updateUserUseCase.execute(1L, updateUserDto);
        assertEquals(user.getName(), response.getName(),"the name wasn't changed");
    }
}
