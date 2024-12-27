package org.example.rfshop.User.domain.Dto.Request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    @Size(min = 3, max = 50, message = "The username must be between 3 and 50 characters")
    private String name;

    @Size(min = 3, max = 50, message = "The username must be between 3 and 50 characters")
    private String lastName;
}
