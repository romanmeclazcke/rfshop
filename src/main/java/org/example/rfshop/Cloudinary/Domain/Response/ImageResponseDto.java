package org.example.rfshop.Cloudinary.Domain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDto {
    private String secure_url;
    private String asset_folder;
    private String public_id;

}
