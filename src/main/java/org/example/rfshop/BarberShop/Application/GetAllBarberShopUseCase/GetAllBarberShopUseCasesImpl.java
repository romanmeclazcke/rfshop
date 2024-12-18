package org.example.rfshop.BarberShop.Application.GetAllBarberShopUseCase;

import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Mapper.BarberShopMapper;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBarberShopUseCasesImpl implements GetAllBarberShopUseCase {

    private final BarberShopRepository barberShopRepository;
    private final BarberShopMapper barberShopMapper;

    @Autowired
    public GetAllBarberShopUseCasesImpl(BarberShopRepository barberShopRepository, BarberShopMapper barberShopMapper) {
        this.barberShopRepository = barberShopRepository;
        this.barberShopMapper = barberShopMapper;
    }

    @Override
    public List<BarberShopResponseDto> execute() {
        return this.barberShopRepository.findAll().stream().map(this.barberShopMapper::toDto).toList();
    }
}
