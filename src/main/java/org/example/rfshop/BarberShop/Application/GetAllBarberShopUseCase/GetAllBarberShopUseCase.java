package org.example.rfshop.BarberShop.Application.GetAllBarberShopUseCase;

import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;

import java.util.List;

public interface GetAllBarberShopUseCase {

    List<BarberShop> getAllBarberShops();
}
