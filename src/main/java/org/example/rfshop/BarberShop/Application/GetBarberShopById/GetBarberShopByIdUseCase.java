package org.example.rfshop.BarberShop.Application.GetBarberShopById;

import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;

public interface GetBarberShopByIdUseCase {

    BarberShopResponseDto execute(Long id);
}
