package org.example.rfshop.User.Infrastructure.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.rfshop.User.Application.CreateUserUseCase.CreateUserUseCase;
import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    @Autowired
    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        System.out.println(createUserDto);
        return new ResponseEntity<>(this.createUserUseCase.createUser(createUserDto), HttpStatus.CREATED);
    }

}
