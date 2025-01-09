package org.example.rfshop.Booking.Infrastructure.Exception;

public class BookingAlreadyReserved extends RuntimeException {
    public BookingAlreadyReserved(String message) {
        super(message);
    }
}
