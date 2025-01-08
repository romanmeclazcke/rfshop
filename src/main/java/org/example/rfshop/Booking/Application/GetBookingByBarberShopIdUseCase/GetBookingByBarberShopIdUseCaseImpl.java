package org.example.rfshop.Booking.Application.GetBookingByBarberShopIdUseCase;

import org.example.rfshop.Booking.Domain.Response.ListBookingResponseDto;
import org.example.rfshop.Booking.Infrastructure.Exception.InvalidMonth;
import org.example.rfshop.Booking.Infrastructure.Mapper.BookingMapper;
import org.example.rfshop.Booking.Infrastructure.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBookingByBarberShopIdUseCaseImpl implements GetBookingByBarberShopIdUseCase {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public GetBookingByBarberShopIdUseCaseImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public List<ListBookingResponseDto> execute(Long barberShopId, Long monthId) {
        try {
            if (monthId < 1 || monthId > 12) {
                throw new InvalidMonth("Month must be between 1 and 12");
            }
            return this.bookingRepository.getBookingByBarberShopIdAndMonth(barberShopId, monthId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
