package org.example.rfshop.Court.Application.DeleteCourtUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Court.Infrastructure.Repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeleteCourtUseCaseImpl implements DeleteCourtUseCases{

    private final CourtRepository courtRepository;

    @Autowired
    public DeleteCourtUseCaseImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public void execute(Long courtId) {
        this.courtRepository.findById(courtId).ifPresentOrElse(
            this.courtRepository::delete,
                ()->{ throw new EntityNotFoundException("Court with id"+ courtId+" not found");
        });
    }
}
