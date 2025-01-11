package org.example.rfshop.Cloudinary.Application.ExtractIdFromPubicUrl;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ExtractIdFromPublicUrlImpl  implements  ExtractIdFromPublicUrl{

    @Override
    public Optional<String> execute(String secureUrl) {
        try {
            String[] parts = secureUrl.split("/");

            String folder = parts[parts.length - 2]; //folder name
            String fileWithExtension = parts[parts.length - 1]; //public id and extension

            String fileName = fileWithExtension.split("\\.")[0]; //remove extension

            String publicId = folder + "/" + fileName; // concat folder name and public id

            return Optional.of(publicId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
