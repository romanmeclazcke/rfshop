package org.example.rfshop.BarberShopHistory.Infrastructure.Mapper;

import org.example.rfshop.BarberShopHistory.Domain.Dto.Response.FavoriteBarberShopResponseDto;
import org.example.rfshop.BarberShopHistory.Infrastructure.Model.FavoriteBarberShop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoriteBarberShopMapper {

    FavoriteBarberShopResponseDto toDto(FavoriteBarberShop favoriteBarberShop);
}
