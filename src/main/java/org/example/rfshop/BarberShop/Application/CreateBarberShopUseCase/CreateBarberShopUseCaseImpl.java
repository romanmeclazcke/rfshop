package org.example.rfshop.BarberShop.Application.CreateBarberShopUseCase;


import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBarberShopUseCaseImpl implements CreateBarberShopUseCase {

    private BarberShopRepository barberShopRepository;

    @Autowired
    public CreateBarberShopUseCaseImpl(BarberShopRepository barberShopRepository) {
        this.barberShopRepository = barberShopRepository;
    }


    @Override
    public BarberShopResponseDto createBarberShop(CreateBarberShopDto createBarberShopDto) {
        return null;
    }
}
