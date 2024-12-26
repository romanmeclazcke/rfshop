package org.example.rfshop.FavoriteBarberShop.Application.AddBarberShopToFavoriteUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Exception.BarberShopAlreadyAddedException;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Mapper.FavoriteBarberShopMapper;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShop;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShopId;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Repository.FavoriteBarberShopRepository;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBarberShopToFavoriteUseCaseImpl implements AddBarberShopToFavoriteUseCase {

    private final FavoriteBarberShopRepository favoriteBarberShopRepository;
    private final FavoriteBarberShopMapper barberShopHistoryMapper;
    private final UserRepository userRepository;
    private final BarberShopRepository barberShopRepository;


    @Autowired
    public AddBarberShopToFavoriteUseCaseImpl(FavoriteBarberShopRepository favoriteBarberShopRepository, FavoriteBarberShopMapper barberShopHistoryMapper, UserRepository userRepository, BarberShopRepository barberShopRepository) {
        this.favoriteBarberShopRepository = favoriteBarberShopRepository;
        this.barberShopHistoryMapper = barberShopHistoryMapper;
        this.userRepository = userRepository;
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public FavoriteBarberShopResponseDto execute(Long userId, Long barberShopId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
        BarberShop barberShop = this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id " + barberShopId + " not found"));

        FavoriteBarberShopId id = new FavoriteBarberShopId(user.getId(), barberShop.getId());

        this.favoriteBarberShopRepository.findById(id)
                .ifPresent(history -> {
                    throw new BarberShopAlreadyAddedException("BarberShop with id " + barberShopId + " already added to history");
                });

        FavoriteBarberShop favoriteBarberShop = FavoriteBarberShop.builder()
                                                                              .id(id)
                                                                              .user(user)
                                                                              .barberShop(barberShop).build();
        return this.barberShopHistoryMapper.toDto(this.favoriteBarberShopRepository.save(favoriteBarberShop));
    }
}
