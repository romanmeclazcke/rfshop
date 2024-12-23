package org.example.rfshop.Cloudinary.infrastructure.Config.Exception;

public class FailToUploadImage extends RuntimeException {
    public FailToUploadImage(String message) {
        super(message);
    }
}
