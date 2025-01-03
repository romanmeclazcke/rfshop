package org.example.rfshop.User.domain.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.User.Infrastructure.Model.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private Role role;
}
