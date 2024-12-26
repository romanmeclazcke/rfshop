package org.example.rfshop.FavoriteBarberShop.Application.AddBarberShopToFavoriteUseCase;

import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;

public interface AddBarberShopToFavoriteUseCase {
    FavoriteBarberShopResponseDto execute(Long userId, Long barberShopId);
}
