package org.example.rfshop.Post.Domain.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDto {

    @NotBlank(message = "URL image cannot be blank")
    private String urlImage;

    @NotBlank(message = "Description cannot be blank")
    private String description;

}
