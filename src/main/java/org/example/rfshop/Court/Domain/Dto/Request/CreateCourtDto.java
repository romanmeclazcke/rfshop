package org.example.rfshop.Court.Domain.Dto.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourtDto {

    @NotNull(message = "The price of court cannot be null")
    private Double price;
    @NotNull(message = "the date of start of price court cannot be null")
    private Integer duration;
    @NotNull(message = "the date of start of price court cannot be null")
    private Date startDate;
}
