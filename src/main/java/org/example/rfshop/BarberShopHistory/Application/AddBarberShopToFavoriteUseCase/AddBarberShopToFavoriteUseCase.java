package org.example.rfshop.BarberShopHistory.Application.AddBarberShopToFavoriteUseCase;

import org.example.rfshop.BarberShopHistory.Domain.Dto.Response.FavoriteBarberShopResponseDto;

public interface AddBarberShopToFavoriteUseCase {
    FavoriteBarberShopResponseDto execute(Long userId, Long barberShopId);
}
