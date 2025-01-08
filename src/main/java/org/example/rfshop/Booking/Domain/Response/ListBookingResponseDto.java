package org.example.rfshop.Booking.Domain.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListBookingResponseDto {
    private LocalDateTime date;
    private Long limitBookingAtSameTime;
    private Long currentBooking;
}
