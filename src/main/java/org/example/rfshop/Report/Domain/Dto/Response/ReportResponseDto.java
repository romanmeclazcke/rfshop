package org.example.rfshop.Report.Domain.Dto.Response;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;

    private String description;

    private Date date;

    private String status;

    private ReviewResponseDto review;
}
