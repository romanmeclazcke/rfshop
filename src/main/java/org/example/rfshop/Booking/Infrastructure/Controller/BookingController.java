package org.example.rfshop.Booking.Infrastructure.Controller;

import org.example.rfshop.Booking.Application.GetBookingByBarberShopIdUseCase.GetBookingByBarberShopIdUseCase;
import org.example.rfshop.Booking.Domain.Response.ListBookingResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final GetBookingByBarberShopIdUseCase   getBookingByBarberShopIdUseCase;


    public BookingController(GetBookingByBarberShopIdUseCase getBookingByBarberShopIdUseCase) {
        this.getBookingByBarberShopIdUseCase = getBookingByBarberShopIdUseCase;
    }

    @GetMapping("/barber-shop/{barberShopId}/month/{monthId}")
    public ResponseEntity<?> getBookingsByBarberShopIdAndMonth(@PathVariable Long barberShopId, @PathVariable Long monthId) {
        List<ListBookingResponseDto> bookings = getBookingByBarberShopIdUseCase.execute(barberShopId, monthId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

}
