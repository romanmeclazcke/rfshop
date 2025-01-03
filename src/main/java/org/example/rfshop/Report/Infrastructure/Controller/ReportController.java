package org.example.rfshop.Report.Infrastructure.Controller;

import org.example.rfshop.Report.Application.CreateReportUseCase.CreateReportUseCase;
import org.example.rfshop.Report.Domain.Dto.Request.CreateReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final CreateReportUseCase createReportUseCase;

    @Autowired
    public ReportController(CreateReportUseCase createReportUseCase) {
        this.createReportUseCase = createReportUseCase;
    }

    @PostMapping("/review/{reviewId}")
    public ResponseEntity<?> createReport(@PathVariable Long reviewId, @RequestBody CreateReportDto createReportDto) throws MessagingException {
            return new ResponseEntity<>(this.createReportUseCase.execute(reviewId, createReportDto), HttpStatus.CREATED);
    }
}
