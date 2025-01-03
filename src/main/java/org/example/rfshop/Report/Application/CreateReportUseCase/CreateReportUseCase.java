package org.example.rfshop.Report.Application.CreateReportUseCase;

import org.example.rfshop.Report.Domain.Dto.Request.CreateReportDto;
import org.example.rfshop.Report.Domain.Dto.Response.ReportResponseDto;

import javax.mail.MessagingException;

public interface CreateReportUseCase {
    ReportResponseDto execute(Long reviewId, CreateReportDto createReportDto) throws MessagingException;
}
