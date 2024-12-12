package org.example.rfshop.User.Infrastructure.Controller;

import lombok.RequiredArgsConstructor;
import org.example.rfshop.User.Application.CreateUserUseCase.CreateUserUseCase;
import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    @Autowired
    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @GetMapping("/new")
    public void get(){
        this.createUserUseCase.createUser(new CreateUserDto());
    }

}
