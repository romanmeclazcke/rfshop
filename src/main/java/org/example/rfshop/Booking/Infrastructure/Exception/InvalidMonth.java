package org.example.rfshop.Booking.Infrastructure.Exception;

public class InvalidMonth extends RuntimeException {
    public InvalidMonth(String message) {
        super(message);
    }
}
