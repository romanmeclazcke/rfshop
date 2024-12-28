package org.example.rfshop.BarberShop.Application.CreateBarberShopUseCase;


import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.BarberShop.Infrastructure.Exception.InvalidRole;
import org.example.rfshop.BarberShop.Infrastructure.Mapper.BarberShopMapper;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CreateBarberShopUseCaseImpl implements CreateBarberShopUseCase {

    private final BarberShopRepository barberShopRepository;
    private final BarberShopMapper barberShopMapper;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public CreateBarberShopUseCaseImpl(BarberShopRepository barberShopRepository, BarberShopMapper barberShopMapper, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext ExtractUserEmailFromSecurityContext) {
        this.barberShopRepository = barberShopRepository;
        this.barberShopMapper = barberShopMapper;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = ExtractUserEmailFromSecurityContext;
    }


    @Override
    public BarberShopResponseDto execute(CreateBarberShopDto createBarberShopDto) {
        String email = this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext());
        User user = this.getUserByEmail.execute(email);

        if (!"BARBER".equals(user.getRole().getName())) {
            throw new InvalidRole("If the user doesn't have role Barber, they cannot create a BarberShop");
        }

        BarberShop barberShop = this.barberShopMapper.toEntity(createBarberShopDto);
        barberShop.setOwner(user);
        this.barberShopRepository.save(barberShop);
        return this.barberShopMapper.toDto(barberShop);
    }
}