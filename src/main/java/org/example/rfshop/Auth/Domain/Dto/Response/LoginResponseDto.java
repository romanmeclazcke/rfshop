package org.example.rfshop.Auth.Domain.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private Date createdAt;
}
