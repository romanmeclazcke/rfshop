package org.example.rfshop.FavoriteBarberShop.Application.DeleteBarberShopToFavoriteUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShopId;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Repository.FavoriteBarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBarberShopToFavoriteUseCaseImpl implements DeleteBarberShopToFavoriteUseCase {


    private final FavoriteBarberShopRepository favoriteBarberShopRepository;
    private final BarberShopRepository barberShopRepository;
    private final UserRepository userRepository;

    @Autowired
    public DeleteBarberShopToFavoriteUseCaseImpl(FavoriteBarberShopRepository favoriteBarberShopRepository, BarberShopRepository barberShopRepository, UserRepository userRepository) {
        this.favoriteBarberShopRepository = favoriteBarberShopRepository;
        this.barberShopRepository = barberShopRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Long userId, Long barberShopId) {
        this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("Barber shop with id  " + barberShopId + " not found"));
        this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id  " + userId + " not found"));

        this.favoriteBarberShopRepository.findById(new FavoriteBarberShopId(userId, barberShopId)).
                ifPresentOrElse(favorite -> this.favoriteBarberShopRepository.delete(favorite),
                        () -> {
                            throw new EntityNotFoundException("User with id " + userId + " not have barber shop with id " + barberShopId);
                        });
    }
}
