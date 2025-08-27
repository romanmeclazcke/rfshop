package org.example.rfshop.User.CreateUserUseCase;

import org.example.rfshop.User.Application.CreateUserUseCase.CreateUserUseCaseImpl;
import org.example.rfshop.User.Infrastructure.Mapper.UserMapper;
import org.example.rfshop.User.Infrastructure.Model.Role;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.RoleRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    private static CreateUserDto createUserDto;

    @BeforeEach
    public void setup() {
        createUserDto = CreateUserDto.builder()
                .email("usertest2@gmail.com")
                .name("test")
                .lastName("test")
                .password("test")
                .rolId(1L)
                .build();

        // Inicializar la implementación concreta
        createUserUseCase = new CreateUserUseCaseImpl(
                userRepository,
                userMapper,
                passwordEncoder,
                roleRepository
        );
    }

    @Test
    public void createUser() {
        // Given
        Role role = new Role(1L, "USER");
        User user = User.builder()
                .email(createUserDto.getEmail())
                .name(createUserDto.getName())
                .lastName(createUserDto.getLastName())
                .password("encodedPassword")
                .role(role)
                .build();

        UserResponseDto expectedResponse = UserResponseDto.builder()
                .id(null)
                .email(createUserDto.getEmail())
                .name(createUserDto.getName())
                .lastName(createUserDto.getLastName())
                .password("encodedPassword")
                .role(role)
                .build();

        // Configurar los mocks
        when(userRepository.findUserByEmail(createUserDto.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userMapper.toEntity(any(CreateUserDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(expectedResponse);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        UserResponseDto result = createUserUseCase.execute(createUserDto);

        // Then
        assertNotNull(result);
        assertEquals(createUserDto.getEmail(), result.getEmail());
        assertEquals(createUserDto.getName(), result.getName());
        assertEquals(createUserDto.getLastName(), result.getLastName());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(role, result.getRole());

        // Verify interactions
        verify(userRepository).findUserByEmail(createUserDto.getEmail());
        verify(roleRepository).findById(1L);
        verify(passwordEncoder).encode(anyString());
        verify(userMapper).toEntity(any(CreateUserDto.class));
        verify(userMapper).toDto(any(User.class));
        verify(userRepository).save(any(User.class));
    }
}