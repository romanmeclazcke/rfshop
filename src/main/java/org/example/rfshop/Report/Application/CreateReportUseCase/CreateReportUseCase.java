package org.example.rfshop.Report.Application.CreateReportUseCase;

import org.example.rfshop.Report.Domain.Dto.Request.CreateReportDto;
import org.example.rfshop.Report.Domain.Dto.Response.ReportResponseDto;

public interface CreateReportUseCase {
    ReportResponseDto execute(Long reviewId, CreateReportDto createReportDto);
}
