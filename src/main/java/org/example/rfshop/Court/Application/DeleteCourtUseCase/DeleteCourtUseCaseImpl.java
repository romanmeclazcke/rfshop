package org.example.rfshop.Court.Application.DeleteCourtUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.example.rfshop.Court.Infrastructure.Repository.CourtRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.Utils.Exception.DeniedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class DeleteCourtUseCaseImpl implements DeleteCourtUseCases{

    private final CourtRepository courtRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public DeleteCourtUseCaseImpl(CourtRepository courtRepository, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.courtRepository = courtRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public void execute(Long courtId) {
        Court court = this.courtRepository.findById(courtId).orElseThrow(() -> new EntityNotFoundException("Court with id" + courtId + " not found"));

        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));

        if (!user.getId().equals(court.getBarberShop().getOwner().getId())) {  //check if user that are deleting a court is  the owner of barber shop
            throw new DeniedAction("You are not the owner of the barber shop");
        }

        this.courtRepository.delete(court);
    }
}
