package org.example.rfshop.FavoriteBarberShop.Application.AddBarberShopToFavoriteUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Exception.BarberShopAlreadyAddedException;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Mapper.FavoriteBarberShopMapper;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShop;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShopId;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Repository.FavoriteBarberShopRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AddBarberShopToFavoriteUseCaseImpl implements AddBarberShopToFavoriteUseCase {

    private final FavoriteBarberShopRepository favoriteBarberShopRepository;
    private final FavoriteBarberShopMapper barberShopHistoryMapper;
    private final BarberShopRepository barberShopRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;


    @Autowired
    public AddBarberShopToFavoriteUseCaseImpl(FavoriteBarberShopRepository favoriteBarberShopRepository, FavoriteBarberShopMapper barberShopHistoryMapper , BarberShopRepository barberShopRepository, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.favoriteBarberShopRepository = favoriteBarberShopRepository;
        this.barberShopHistoryMapper = barberShopHistoryMapper;
        this.barberShopRepository = barberShopRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public FavoriteBarberShopResponseDto execute(Long barberShopId) {
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
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
