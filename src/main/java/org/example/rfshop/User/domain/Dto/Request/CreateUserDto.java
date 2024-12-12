package org.example.rfshop.User.domain.Dto.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @Email(message = "The email should be valid")
    @NotEmpty(message = "The email cannot be empty")
    private String email;

    @NotEmpty(message = "The username cannot be empty")
    @Size(min = 3, max = 50, message = "The username must be between 3 and 50 characters")
    private String userName;

    @NotEmpty(message = "The last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "The password cannot be empty")
    @Size(min = 6, message = "The password must be at least 6 characters long")
    private String password;
}
