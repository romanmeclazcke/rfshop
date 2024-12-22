package org.example.rfshop.Cloudinary.Application.UploadImageUseCase;

import com.cloudinary.Cloudinary;
import org.example.rfshop.Cloudinary.Domain.Response.ImageResponseDto;
import org.example.rfshop.Post.Infrastructure.Exception.FailToUploadImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
public class UploadImageUseCaseImpl implements UploadImageUseCase {

    private final Cloudinary cloudinary;

    public UploadImageUseCaseImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public ImageResponseDto execute(MultipartFile file, String folder) {
        try {
            Map<String, Object> uploadResult = this.cloudinary.uploader().upload(file.getBytes(), com.cloudinary.utils.ObjectUtils.asMap("folder", folder));
            return this.mapToImageResponseDto(uploadResult);
        } catch (IOException e) {
            throw new FailToUploadImage("Fail to upload image");
        }
    }

    private ImageResponseDto mapToImageResponseDto(Map<String, Object> result){
        return ImageResponseDto.builder()
                .secure_url((String) result.get("secure_url"))
                .asset_folder((String) result.get("asset_folder"))
                .public_id((String) result.get("public_id"))
                .build();
    }
}
