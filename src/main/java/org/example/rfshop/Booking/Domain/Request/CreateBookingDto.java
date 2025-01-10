package org.example.rfshop.Booking.Domain.Request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto {
    @NotNull(message = "The start of booking cannot be null")
    private LocalDateTime bookingStart;

    @NotNull(message = "The end of booking cannot be null")
    private LocalDateTime bookingEnd;

    @NotNull(message = "The number of booking at Same Time cannot be null ")
    private Integer numbersOfBookingAtSameTime;

    private Boolean reserved=false;
}
