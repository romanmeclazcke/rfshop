package org.example.rfshop.Booking.Application.CreateBookingUseCase;

import org.example.rfshop.Booking.Domain.Request.CreateBookingDto;
import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;

import java.util.List;

public interface CreateBookingUseCase {
    List<BookingResponseDto> execute(Long barberShopId,List<CreateBookingDto> createBookingDto);
}
