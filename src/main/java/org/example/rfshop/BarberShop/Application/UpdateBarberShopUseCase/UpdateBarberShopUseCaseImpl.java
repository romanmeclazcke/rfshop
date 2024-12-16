package org.example.rfshop.BarberShop.Application.UpdateBarberShopUseCase;


import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBarberShopUseCaseImpl  implements UpdateBarberShopUseCase {

    private BarberShopRepository barberShopRepository;

    @Autowired
    public UpdateBarberShopUseCaseImpl(BarberShopRepository barberShopRepository) {
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public BarberShopResponseDto updateBarberShop(Long id,UpdateBarberShopDto updateBarberShopDto) {
        BarberShop barberShop = this.barberShopRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("BarberShop with id " +id + " not found"));

        Optional.ofNullable(null).ifPresent(null);
        return null;
    }
}
