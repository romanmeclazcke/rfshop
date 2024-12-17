package org.example.rfshop.BarberShop.Application.CreateBarberShopUseCase;

import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;

public interface CreateBarberShopUseCase {
    BarberShopResponseDto execute(Long ownerId,CreateBarberShopDto createBarberShopDto);
}
