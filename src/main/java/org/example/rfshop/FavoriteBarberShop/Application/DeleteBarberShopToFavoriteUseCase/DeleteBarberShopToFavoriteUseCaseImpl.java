package org.example.rfshop.FavoriteBarberShop.Application.DeleteBarberShopToFavoriteUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShopId;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Repository.FavoriteBarberShopRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeleteBarberShopToFavoriteUseCaseImpl implements DeleteBarberShopToFavoriteUseCase {


    private final FavoriteBarberShopRepository favoriteBarberShopRepository;
    private final BarberShopRepository barberShopRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public DeleteBarberShopToFavoriteUseCaseImpl(FavoriteBarberShopRepository favoriteBarberShopRepository, BarberShopRepository barberShopRepository,GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.favoriteBarberShopRepository = favoriteBarberShopRepository;
        this.barberShopRepository = barberShopRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public void execute(Long barberShopId) {
        String email = this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext());
        User user= this.getUserByEmail.execute(email);
        this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("Barber shop with id  " + barberShopId + " not found"));

        this.favoriteBarberShopRepository.findById(new FavoriteBarberShopId(user.getId(), barberShopId)).
                ifPresentOrElse(this.favoriteBarberShopRepository::delete,
                        () -> {
                            throw new EntityNotFoundException("User not have barber shop with id " + barberShopId + "added to favorite");
                        });
    }
}
