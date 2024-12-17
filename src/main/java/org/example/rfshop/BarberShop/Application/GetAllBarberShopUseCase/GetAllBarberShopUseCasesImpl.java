package org.example.rfshop.BarberShop.Application.GetAllBarberShopUseCase;

import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBarberShopUseCasesImpl implements GetAllBarberShopUseCase {

    private final BarberShopRepository barberShopRepository;

    @Autowired
    public GetAllBarberShopUseCasesImpl(BarberShopRepository barberShopRepository) {
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public List<BarberShop> getAllBarberShops() {
        return this.barberShopRepository.findAll();
    }
}
