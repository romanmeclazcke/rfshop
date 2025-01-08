package org.example.rfshop.Booking.Application.GetBookingByBarberShopIdUseCase;

import org.example.rfshop.Booking.Domain.Response.ListBookingResponseDto;

import java.util.List;

public interface GetBookingByBarberShopIdUseCase {
    List<ListBookingResponseDto> execute(Long barberShopId, Long monthId);
}
