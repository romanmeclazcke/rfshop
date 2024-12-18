package org.example.rfshop.Court.Application.UpdateCourtUseCase;

import org.example.rfshop.Court.Domain.Dto.Request.UpdateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;

public interface UpdateCourtUseCase {
    CourtResponseDto execute(Long courtId, UpdateCourtDto updateCourtDto);
}


