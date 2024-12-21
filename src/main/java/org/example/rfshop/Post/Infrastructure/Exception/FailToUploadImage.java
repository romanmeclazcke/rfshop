package org.example.rfshop.Post.Infrastructure.Exception;

public class FailToUploadImage extends RuntimeException {
    public FailToUploadImage(String message) {
        super(message);
    }
}
