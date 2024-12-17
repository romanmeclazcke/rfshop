package org.example.rfshop.BarberShop.Application.CreateBarberShopUseCase;


import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EnumType;
import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Exception.InvalidRole;
import org.example.rfshop.BarberShop.Infrastructure.Mapper.BarberShopMapper;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.example.rfshop.User.domain.Dto.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CreateBarberShopUseCaseImpl implements CreateBarberShopUseCase {

    private BarberShopRepository barberShopRepository;
    private UserRepository userRepository;
    private BarberShopMapper barberShopMapper;

    @Autowired
    public CreateBarberShopUseCaseImpl(BarberShopRepository barberShopRepository, UserRepository userRepository, BarberShopMapper barberShopMapper) {
        this.barberShopRepository = barberShopRepository;
        this.userRepository = userRepository;
        this.barberShopMapper = barberShopMapper;
    }


    @Override
    public BarberShopResponseDto createBarberShop(Long ownerId, CreateBarberShopDto createBarberShopDto) {
        return this.userRepository.findById(ownerId)
                .map(user -> {
                    System.out.println(user.getRole().getName());
                    if (!"BARBER".equals(user.getRole().getName())) {
                        throw new InvalidRole("If the user doesn't have role Barber, they cannot create a BarberShop");
                    }
                    BarberShop barberShop =this.barberShopMapper.toEntity(createBarberShopDto);
                    barberShop.setOwner(user);
                    this.barberShopRepository.save(barberShop);
                    return this.barberShopMapper.toDto(barberShop);
                })
                .orElseThrow(() -> new EntityNotFoundException("User owner not found"));
    }
}
