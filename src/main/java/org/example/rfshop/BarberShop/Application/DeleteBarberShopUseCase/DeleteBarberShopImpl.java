package org.example.rfshop.BarberShop.Application.DeleteBarberShopUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.BarberShop.Infrastructure.Exception.DeniedAction;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeleteBarberShopImpl implements DeleteBarberShop {


    private final BarberShopRepository barberShopRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public DeleteBarberShopImpl(BarberShopRepository barberShopRepository, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.barberShopRepository = barberShopRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public void execute(Long barberId) {
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
        BarberShop barberShop = this.barberShopRepository.findById(barberId).orElseThrow(()-> new EntityNotFoundException("Barber with id " + barberId + " not found"));
        if(!barberShop.getOwner().getId().equals(user.getId())){
            throw  new DeniedAction("Only the owner of barber shop can deleted it");
        }
        this.barberShopRepository.deleteById(barberId);
    }
}
