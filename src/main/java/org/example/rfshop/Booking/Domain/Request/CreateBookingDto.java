package org.example.rfshop.Booking.Domain.Request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto {
    @NotNull
    private LocalDateTime bookingStart;

    @NotNull
    private LocalDateTime bookingEnd;

    private Boolean reserved=false;
}
