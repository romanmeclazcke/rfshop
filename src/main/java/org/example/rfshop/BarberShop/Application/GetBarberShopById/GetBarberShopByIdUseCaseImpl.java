package org.example.rfshop.BarberShop.Application.GetBarberShopById;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Mapper.BarberShopMapper;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBarberShopByIdUseCaseImpl implements GetBarberShopByIdUseCase {

    private BarberShopRepository barberShopRepository;
    private BarberShopMapper barberShopMapper;

    @Autowired
    public GetBarberShopByIdUseCaseImpl(BarberShopRepository barberShopRepository, UserRepository userRepository, BarberShopMapper barberShopMapper) {
        this.barberShopRepository = barberShopRepository;
        this.barberShopMapper = barberShopMapper;
    }

    @Override
    public BarberShopResponseDto execute(Long id) {
        return this.barberShopRepository.findById(id)
                .map(this.barberShopMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Barber with id " + id + " not found"));
    }
}
