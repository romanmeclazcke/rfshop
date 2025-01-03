package org.example.rfshop.FavoriteBarberShop.Application.GetAllFavoriteBarberShopByUserId;

import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;

import java.util.List;

public interface GetAllFavoriteBarberShopByUserId {

    List<FavoriteBarberShopResponseDto> execute(Long userId);
}
