package org.example.rfshop.Court.Application.CreateCourtUseCase;

import org.example.rfshop.Court.Domain.Dto.Request.CreateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;
import org.example.rfshop.Court.Infrastructure.Mapper.CourtMapper;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.example.rfshop.Court.Infrastructure.Repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateCourtUseCaseImpl implements CreateCourtUseCase {

    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;

    @Autowired
    public CreateCourtUseCaseImpl(CourtRepository courtRepository,CourtMapper courtMapper) {
        this.courtRepository = courtRepository;
        this.courtMapper = courtMapper;
    }

    @Override
    public CourtResponseDto execute(CreateCourtDto createCourtDto) {
        Court court = this.courtMapper.toEntity(createCourtDto);
        return this.courtMapper.toDto(this.courtRepository.save(court));
    }
}