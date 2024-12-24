package org.example.rfshop.Cloudinary.Application.DeleteImageUseCase;

import com.cloudinary.Cloudinary;
import org.example.rfshop.Cloudinary.Application.ExtractIdFromPubicUrl.ExtractIdFromPublicUrl;
import org.example.rfshop.Cloudinary.infrastructure.Config.Exception.PublicIdNotFound;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class DeleteImageUseCaseImpl implements DeleteImageUseCase{

    private  final Cloudinary cloudinary;
    private final ExtractIdFromPublicUrl extractIdFromPublicUrl;

    public DeleteImageUseCaseImpl(Cloudinary cloudinary, ExtractIdFromPublicUrl ExtractIdFromPublicUrl) {
        this.cloudinary = cloudinary;
        this.extractIdFromPublicUrl =  ExtractIdFromPublicUrl;
    }
    @Override
    public Boolean execute(String secureUrl) throws IOException {
        try{
            String publicId= this.extractIdFromPublicUrl.execute(secureUrl).orElseThrow(()-> new PublicIdNotFound("Cannot extract public id from url" + secureUrl));
            Map result = this.cloudinary.uploader().destroy(publicId, null);
            return "ok".equals(result.get("result"));
        } catch (IOException e) {
            throw new RuntimeException("Error while deleting image");
        }
    }


}
