package org.example.rfshop.User.domain.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameDto {
    private Long id;
    private String name;
    private String lastName;
}
