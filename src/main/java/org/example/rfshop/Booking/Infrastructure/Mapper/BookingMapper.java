package org.example.rfshop.Booking.Infrastructure.Mapper;

import org.example.rfshop.Booking.Domain.Request.CreateBookingDto;
import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;
import org.example.rfshop.Booking.Infrastructure.Model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toEntity(CreateBookingDto createBookingDto);
    BookingResponseDto toDto(Booking booking);
}
