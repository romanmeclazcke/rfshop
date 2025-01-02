package org.example.rfshop.Report.Infrastructure.Mapper;

import org.example.rfshop.Report.Domain.Dto.Request.CreateReportDto;
import org.example.rfshop.Report.Domain.Dto.Response.ReportResponseDto;
import org.example.rfshop.Report.Infrastructure.Model.Report;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface ReportMapper {

    @Mapping(source = "review", target = "review")
    ReportResponseDto toDto(Report report);

    Report toEntity(CreateReportDto createReportDto);
}
