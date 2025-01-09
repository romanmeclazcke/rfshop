package org.example.rfshop.Booking.Application.GetBookingByBarberShopIdUseCase;

import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;
import org.example.rfshop.Booking.Infrastructure.Exception.InvalidMonth;
import org.example.rfshop.Booking.Infrastructure.Mapper.BookingMapper;
import org.example.rfshop.Booking.Infrastructure.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
    public List<BookingResponseDto> execute(Long barberShopId, Long monthId) {
        try {
            if (monthId < 1 || monthId > 12) {
                throw new InvalidMonth("Month must be between 1 and 12");
            }
            // current year
            int currentYear = LocalDate.now().getYear();

            // Range of dates
            YearMonth yearMonth = YearMonth.of(currentYear, monthId.intValue());
            LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay(); // First day of the month
            LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59); // Last day of the month

            return this.bookingRepository.findBookingsByBarberShopAndMonth(barberShopId, startDate, endDate)
                    .stream()
                    .map(this.bookingMapper::toDto)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error while executing booking retrieval", e);
        }
    }

}
