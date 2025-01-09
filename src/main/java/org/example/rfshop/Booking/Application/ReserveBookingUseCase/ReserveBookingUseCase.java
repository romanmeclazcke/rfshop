package org.example.rfshop.Booking.Application.ReserveBookingUseCase;

import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;

public interface ReserveBookingUseCase {
    BookingResponseDto execute(Long bookingId);
}
