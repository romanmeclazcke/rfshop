package org.example.rfshop.BarberShop.Infrastructure.Mapper;

import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.mapstruct.Mapper;

@Mapper
public interface BarberShopMapper {

    BarberShop toEntity(CreateBarberShopDto createBarberShopDto);
    BarberShopResponseDto toDto(BarberShop barberShop);

}
