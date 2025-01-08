package org.example.rfshop.Court.Domain.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourtDto {
    private Double price;
    private Integer duration;
    private Date startDate;
}
