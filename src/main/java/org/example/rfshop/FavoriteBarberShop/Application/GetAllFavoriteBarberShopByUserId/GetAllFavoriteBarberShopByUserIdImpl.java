package org.example.rfshop.FavoriteBarberShop.Application.GetAllFavoriteBarberShopByUserId;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Mapper.FavoriteBarberShopMapper;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Repository.FavoriteBarberShopRepository;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetAllFavoriteBarberShopByUserIdImpl implements GetAllFavoriteBarberShopByUserId {

    private final FavoriteBarberShopRepository favoriteBarberShopRepository;
    private final FavoriteBarberShopMapper barberShopHistoryMapper;
    private final UserRepository userRepository;


    @Autowired
    public GetAllFavoriteBarberShopByUserIdImpl(FavoriteBarberShopRepository favoriteBarberShopRepository, FavoriteBarberShopMapper barberShopHistoryMapper, UserRepository userRepository) {
        this.favoriteBarberShopRepository = favoriteBarberShopRepository;
        this.barberShopHistoryMapper = barberShopHistoryMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<FavoriteBarberShopResponseDto> execute(Long userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id  "+userId+" not found"));
        return this.favoriteBarberShopRepository.findAllFavoriteBarberShopByUserId(userId)
                .stream()
                .map(this.barberShopHistoryMapper::toDto).toList();
    }
}
