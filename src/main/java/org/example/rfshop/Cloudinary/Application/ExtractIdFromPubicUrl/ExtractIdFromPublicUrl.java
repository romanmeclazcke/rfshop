package org.example.rfshop.Cloudinary.Application.ExtractIdFromPubicUrl;

import java.util.Optional;

public interface ExtractIdFromPublicUrl {
     Optional<String> execute(String publicId);
}