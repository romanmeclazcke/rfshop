package org.example.rfshop.Booking.Infrastructure.Controller;

import org.example.rfshop.Booking.Application.CreateBookingUseCase.CreateBookingUseCase;
import org.example.rfshop.Booking.Application.GetBookingByBarberShopIdUseCase.GetBookingByBarberShopIdUseCase;
import org.example.rfshop.Booking.Application.ReserveBookingUseCase.ReserveBookingUseCase;
import org.example.rfshop.Booking.Domain.Request.CreateBookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final GetBookingByBarberShopIdUseCase   getBookingByBarberShopIdUseCase;
    private final CreateBookingUseCase createBookingUseCase;
    private final ReserveBookingUseCase reserveBookingUseCase;

    @Autowired
    public BookingController(GetBookingByBarberShopIdUseCase getBookingByBarberShopIdUseCase, CreateBookingUseCase createBookingUseCase, ReserveBookingUseCase reserveBookingUseCase) {
        this.getBookingByBarberShopIdUseCase = getBookingByBarberShopIdUseCase;
        this.createBookingUseCase = createBookingUseCase;
        this.reserveBookingUseCase = reserveBookingUseCase;
    }

    @GetMapping("/barber-shop/{barberShopId}/month/{monthId}")
    public ResponseEntity<?> getBookingsByBarberShopIdAndMonth(@PathVariable Long barberShopId, @PathVariable Long monthId) {
        return new ResponseEntity<>(getBookingByBarberShopIdUseCase.execute(barberShopId, monthId), HttpStatus.OK);
    }

    @PostMapping("/barber-shop/{barberShopId}")
    public ResponseEntity<?> createBooking(@PathVariable Long barberShopId, @RequestBody List<CreateBookingDto> createBookingDto) {
        return new ResponseEntity<>(this.createBookingUseCase.execute(barberShopId, createBookingDto),HttpStatus.CREATED);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<?> reserveBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(this.reserveBookingUseCase.execute(bookingId),HttpStatus.OK);
    }

}
