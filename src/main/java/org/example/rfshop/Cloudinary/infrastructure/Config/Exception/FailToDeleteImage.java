package org.example.rfshop.Cloudinary.infrastructure.Config.Exception;

public class FailToDeleteImage extends RuntimeException {
    public FailToDeleteImage(String message) {
        super(message);
    }
}
