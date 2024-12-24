package org.example.rfshop.Post.Domain.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String secureUrl;
    private String description;
    private Date createdAt;

}
