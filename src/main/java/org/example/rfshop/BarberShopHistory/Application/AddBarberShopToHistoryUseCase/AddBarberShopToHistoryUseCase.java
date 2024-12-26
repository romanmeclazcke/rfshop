package org.example.rfshop.BarberShopHistory.Application.AddBarberShopToHistoryUseCase;

import org.example.rfshop.BarberShopHistory.Domain.Dto.Response.BarberShopHistoryResponseDto;

public interface AddBarberShopToHistoryUseCase {
    BarberShopHistoryResponseDto execute(Long userId, Long barberShopId);
}
