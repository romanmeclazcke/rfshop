package org.example.rfshop.Post.Domain.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDto {

    @NotBlank(message = "image cannot be blank")
    private MultipartFile image;


    private String description;

}
