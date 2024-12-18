package org.example.rfshop.BarberShop.Application.UpdateBarberShopUseCase;


import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Mapper.BarberShopMapper;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBarberShopUseCaseImpl  implements UpdateBarberShopUseCase {

    private BarberShopRepository barberShopRepository;
    private BarberShopMapper barberShopMapper;

    @Autowired
    public UpdateBarberShopUseCaseImpl(BarberShopRepository barberShopRepository,BarberShopMapper barberShopMapper) {
        this.barberShopRepository = barberShopRepository;
        this.barberShopMapper = barberShopMapper;
    }

    @Override
    public BarberShopResponseDto execute(Long id,UpdateBarberShopDto updateBarberShopDto) {
        BarberShop barberShop = this.barberShopRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("BarberShop with id " +id + " not found"));

        Optional.ofNullable(updateBarberShopDto.getName()).ifPresent(barberShop::setName);
        Optional.ofNullable(updateBarberShopDto.getCity()).ifPresent(barberShop::setCity);
        Optional.ofNullable(updateBarberShopDto.getStreet()).ifPresent(barberShop::setStreet);
        Optional.ofNullable(updateBarberShopDto.getStreetNumber()).ifPresent(barberShop::setStreetNumber);
        Optional.ofNullable(updateBarberShopDto.getPhone()).ifPresent(barberShop::setPhone);

        return this.barberShopMapper.toDto(this.barberShopRepository.save(barberShop));
    }
}
