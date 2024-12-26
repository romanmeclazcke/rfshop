package org.example.rfshop.BarberShopHistory.Application.AddBarberShopToHistoryUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.BarberShopHistory.Domain.Dto.Response.BarberShopHistoryResponseDto;
import org.example.rfshop.BarberShopHistory.Infrastructure.Exception.BarbershopAlreadyAddedException;
import org.example.rfshop.BarberShopHistory.Infrastructure.Mapper.BarberShopHistoryMapper;
import org.example.rfshop.BarberShopHistory.Infrastructure.Model.BarberShopHistory;
import org.example.rfshop.BarberShopHistory.Infrastructure.Model.BarberShopHistoryId;
import org.example.rfshop.BarberShopHistory.Infrastructure.Repository.BarberShopHistoryRepository;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBarberShopToHistoryUseCaseImpl implements AddBarberShopToHistoryUseCase{

    private final BarberShopHistoryRepository barberShopHistoryRepository;
    private final BarberShopHistoryMapper barberShopHistoryMapper;
    private final UserRepository userRepository;
    private final BarberShopRepository barberShopRepository;


    @Autowired
    public AddBarberShopToHistoryUseCaseImpl( BarberShopHistoryRepository barberShopHistoryRepository, BarberShopHistoryMapper barberShopHistoryMapper,UserRepository userRepository, BarberShopRepository barberShopRepository) {
        this.barberShopHistoryRepository = barberShopHistoryRepository;
        this.barberShopHistoryMapper = barberShopHistoryMapper;
        this.userRepository = userRepository;
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public BarberShopHistoryResponseDto execute(Long userId, Long barberShopId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
        BarberShop barberShop = this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id " + barberShopId + " not found"));

        BarberShopHistoryId id = new BarberShopHistoryId(user.getId(), barberShop.getId());

        this.barberShopHistoryRepository.findById(id)
                .ifPresent(history -> {
                    throw new BarbershopAlreadyAddedException("BarberShop with id " + barberShopId + " already added to history");
                });

        BarberShopHistory barberShopHistory = BarberShopHistory.builder()
                                                                              .id(id)
                                                                              .user(user)
                                                                              .barberShop(barberShop).build();
        return this.barberShopHistoryMapper.toDto(this.barberShopHistoryRepository.save(barberShopHistory));
    }
}
