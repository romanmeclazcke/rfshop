package org.example.rfshop.BarberShop.Application.UpdateBarberShopUseCase;


import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Exception.DeniedAction;
import org.example.rfshop.BarberShop.Infrastructure.Mapper.BarberShopMapper;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBarberShopUseCaseImpl  implements UpdateBarberShopUseCase {

    private final BarberShopRepository barberShopRepository;
    private final BarberShopMapper barberShopMapper;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public UpdateBarberShopUseCaseImpl(BarberShopRepository barberShopRepository, BarberShopMapper barberShopMapper, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.barberShopRepository = barberShopRepository;
        this.barberShopMapper = barberShopMapper;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public BarberShopResponseDto execute(Long barberId,UpdateBarberShopDto updateBarberShopDto) {
        BarberShop barberShop = this.barberShopRepository.findById(barberId).orElseThrow(()-> new EntityNotFoundException("Barber with id " + barberId + " not found"));
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));

        if(!barberShop.getOwner().getId().equals(user.getId())){
            throw  new DeniedAction("Only the owner of barber shop can deleted it");
        }

        Optional.ofNullable(updateBarberShopDto.getName()).ifPresent(barberShop::setName);
        Optional.ofNullable(updateBarberShopDto.getCity()).ifPresent(barberShop::setCity);
        Optional.ofNullable(updateBarberShopDto.getStreet()).ifPresent(barberShop::setStreet);
        Optional.ofNullable(updateBarberShopDto.getStreetNumber()).ifPresent(barberShop::setStreetNumber);
        Optional.ofNullable(updateBarberShopDto.getPhone()).ifPresent(barberShop::setPhone);

        return this.barberShopMapper.toDto(this.barberShopRepository.save(barberShop));
    }
}
