package org.example.rfshop.FavoriteBarberShop.Infrastructure.Mapper;

import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteBarberShopMapper {


    @Mapping(target = "barberShop", expression = "java(toBarberInformation(favoriteBarberShop.getBarberShop()))")
    FavoriteBarberShopResponseDto toDto(FavoriteBarberShop favoriteBarberShop);


    default BarberShopResponseDto toBarberInformation(BarberShop barberShop) {
        if (barberShop == null) {
            return null;
        }
        return BarberShopResponseDto.builder()
                .id(barberShop.getId())
                .name(barberShop.getName())
                .city(barberShop.getCity())
                .street(barberShop.getStreet())
                .streetNumber(barberShop.getStreetNumber())
                .chair(barberShop.getChair())
                .phone(barberShop.getPhone())
                .build();
    }
}
