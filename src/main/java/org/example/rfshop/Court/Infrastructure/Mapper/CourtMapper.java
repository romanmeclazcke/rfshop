package org.example.rfshop.Court.Infrastructure.Mapper;

import org.example.rfshop.Court.Domain.Dto.Request.CreateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourtMapper {

    Court toEntity(CreateCourtDto createCourtDto);
    CourtResponseDto toDto(Court court);
}
