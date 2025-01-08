package org.example.rfshop.Court.Application.CreateCourtUseCase;

import org.example.rfshop.Court.Domain.Dto.Request.CreateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;

public interface CreateCourtUseCase {
    CourtResponseDto execute(Long barberShopId,CreateCourtDto createCourtDto);
}
