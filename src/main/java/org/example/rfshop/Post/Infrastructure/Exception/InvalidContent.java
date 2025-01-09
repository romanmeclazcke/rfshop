package org.example.rfshop.Post.Infrastructure.Exception;

public class InvalidContent extends RuntimeException {
    public InvalidContent(String message) {
        super(message);
    }
}
