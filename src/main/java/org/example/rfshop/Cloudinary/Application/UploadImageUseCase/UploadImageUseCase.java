package org.example.rfshop.Cloudinary.Application.UploadImageUseCase;

import org.example.rfshop.Cloudinary.Domain.Response.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImageUseCase {
    ImageResponseDto execute(MultipartFile file, String folder)  ;
}
