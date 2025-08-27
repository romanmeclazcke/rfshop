package org.example.rfshop.User.GetUserByEmailUseCase;

import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmailImpl;
import org.example.rfshop.User.Infrastructure.Model.Role;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
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
public class GetUserByEmailUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByEmailImpl getUserByEmailUseCase;

    private static User user;

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

    }

    @Test
    public void getUserByEmail() {
        when(userRepository.findUserByEmail("usertest2@gmail.com")).thenReturn(Optional.ofNullable(user));
        User response = getUserByEmailUseCase.execute("usertest2@gmail.com");
        assertEquals(response, user, "El usuario no coincide");
    }

    @Test
    public void getUserByEmailWithDifferentEmail() {
        when(userRepository.findUserByEmail("romanmeclazcke@gmail.com")).thenReturn(Optional.ofNullable(user));
        User response = getUserByEmailUseCase.execute("romanmeclazcke@gmail.com");
        assertEquals(response, user, "El usuario no coincide");
    }
}
