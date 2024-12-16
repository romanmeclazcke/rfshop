package org.example.rfshop.User.Infrastructure.Controller;

import jakarta.validation.Valid;
import org.example.rfshop.User.Application.CreateUserUseCase.CreateUserUseCase;
import org.example.rfshop.User.Application.UpdateUserUseCase.UpdateUserUseCase;
import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Request.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Autowired
    public UserController(CreateUserUseCase createUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;

    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return new ResponseEntity<>(this.createUserUseCase.createUser(createUserDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,@RequestBody @Valid UpdateUserDto updateUserDto) {
        return new ResponseEntity<>(this.updateUserUseCase.updateUser(userId,updateUserDto), HttpStatus.OK);
    }
}
