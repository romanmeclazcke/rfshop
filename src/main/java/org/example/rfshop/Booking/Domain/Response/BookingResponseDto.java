package org.example.rfshop.Booking.Domain.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.User.domain.Dto.Response.UserNameDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {
    private Long id;
    private LocalDateTime bookingStart;
    private Boolean state;
    private UserNameDto user;
}
