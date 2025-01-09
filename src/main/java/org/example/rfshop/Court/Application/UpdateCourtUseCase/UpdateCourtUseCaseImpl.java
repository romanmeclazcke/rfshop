package org.example.rfshop.Court.Application.UpdateCourtUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Court.Domain.Dto.Request.UpdateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Response.CourtResponseDto;
import org.example.rfshop.Court.Infrastructure.Mapper.CourtMapper;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.example.rfshop.Court.Infrastructure.Repository.CourtRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.Utils.Exception.DeniedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UpdateCourtUseCaseImpl implements UpdateCourtUseCase {

    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public UpdateCourtUseCaseImpl(CourtRepository courtRepository, CourtMapper courtMapper, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.courtRepository = courtRepository;
        this.courtMapper = courtMapper;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }
    @Override
    public CourtResponseDto execute(Long courtId, UpdateCourtDto updateCourtDto) {
        Court court = this.courtRepository.findById(courtId).orElseThrow(()->new EntityNotFoundException("Court with id"+ courtId+"not found"));

        User currentUser = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));

        if (!currentUser.getId().equals(court.getBarberShop().getOwner().getId())) {  //check if user that are updating a court is  the owner of barber shop
            throw new DeniedAction("You are not the owner of the barber shop");
        }

        Optional.ofNullable(updateCourtDto.getPrice()).ifPresent(court::setPrice);
        Optional.ofNullable(updateCourtDto.getDuration()).ifPresent(court::setDuration);
        Optional.ofNullable(updateCourtDto.getStartDate()).ifPresent(court::setStartDate);

        return this.courtMapper.toDto(this.courtRepository.save(court));
    }
}
