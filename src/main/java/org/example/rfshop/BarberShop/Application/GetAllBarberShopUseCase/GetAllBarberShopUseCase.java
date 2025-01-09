package org.example.rfshop.BarberShop.Application.GetAllBarberShopUseCase;

import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;

import java.util.List;

public interface GetAllBarberShopUseCase {

    List<BarberShopResponseDto> execute();
}
