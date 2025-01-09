package org.example.rfshop.Booking.Application.GetBookingByBarberShopIdUseCase;

import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;

import java.util.List;

public interface GetBookingByBarberShopIdUseCase {
    List<BookingResponseDto> execute(Long barberShopId, Long monthId);
}
