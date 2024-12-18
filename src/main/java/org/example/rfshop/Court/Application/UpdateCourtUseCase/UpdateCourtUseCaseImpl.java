package org.example.rfshop.Court.Application.UpdateCourtUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Court.Domain.Dto.Request.UpdateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;
import org.example.rfshop.Court.Infrastructure.Mapper.CourtMapper;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.example.rfshop.Court.Infrastructure.Repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UpdateCourtUseCaseImpl implements UpdateCourtUseCase {

    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;

    @Autowired
    public UpdateCourtUseCaseImpl(CourtRepository courtRepository,CourtMapper courtMapper) {
        this.courtRepository = courtRepository;
        this.courtMapper = courtMapper;
    }
    @Override
    public CourtResponseDto execute(Long courtId, UpdateCourtDto updateCourtDto) {
        Court court = this.courtRepository.findById(courtId).orElseThrow(()->new EntityNotFoundException("Court with id"+ courtId+"not found"));

        Optional.ofNullable(updateCourtDto.getPrice()).ifPresent(court::setPrice);
        Optional.ofNullable(updateCourtDto.getStartDate()).ifPresent(court::setStartDate);

        return this.courtMapper.toDto(this.courtRepository.save(court));
    }
}
