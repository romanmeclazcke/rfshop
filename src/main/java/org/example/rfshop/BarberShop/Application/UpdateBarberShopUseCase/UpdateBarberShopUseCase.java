package org.example.rfshop.BarberShop.Application.UpdateBarberShopUseCase;

import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;

public interface UpdateBarberShopUseCase {
    BarberShopResponseDto execute(Long barberId,UpdateBarberShopDto updateBarberShopDto);
}
