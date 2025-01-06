package org.example.rfshop.Auth.Infrastructure.Exception;

public class InvalidPassword extends RuntimeException {
    public InvalidPassword(String message) {
        super(message);
    }
}
