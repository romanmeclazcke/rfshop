package org.example.rfshop.Court.Application.CreateCourtUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Court.Domain.Dto.Request.CreateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;
import org.example.rfshop.Court.Infrastructure.Mapper.CourtMapper;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.example.rfshop.Court.Infrastructure.Repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourtUseCaseImpl implements CreateCourtUseCase {

    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;
    private final BarberShopRepository barberShopRepository;

    @Autowired
    public CreateCourtUseCaseImpl(CourtRepository courtRepository, CourtMapper courtMapper, BarberShopRepository barberShopRepository) {
        this.courtRepository = courtRepository;
        this.courtMapper = courtMapper;
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public CourtResponseDto execute(Long barberShopId,CreateCourtDto createCourtDto) { // i need have the barbershop id
        BarberShop barberShop = this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id " + barberShopId + " not found"));
        Court court = this.courtMapper.toEntity(createCourtDto);
        court.setBarberShop(barberShop);
        return this.courtMapper.toDto(this.courtRepository.save(court));
    }
}