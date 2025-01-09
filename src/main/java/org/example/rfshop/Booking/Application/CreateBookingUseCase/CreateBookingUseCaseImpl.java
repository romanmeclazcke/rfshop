package org.example.rfshop.Booking.Application.CreateBookingUseCase;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Booking.Domain.Request.CreateBookingDto;
import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;
import org.example.rfshop.Booking.Infrastructure.Mapper.BookingMapper;
import org.example.rfshop.Booking.Infrastructure.Model.Booking;
import org.example.rfshop.Booking.Infrastructure.Repository.BookingRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.Utils.Exception.DeniedAction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateBookingUseCaseImpl implements CreateBookingUseCase{

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BarberShopRepository barberShopRepository;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;
    private final GetUserByEmail getUserByEmail;

    public CreateBookingUseCaseImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, BarberShopRepository barberShopRepository, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext, GetUserByEmail getUserByEmail) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.barberShopRepository = barberShopRepository;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
        this.getUserByEmail = getUserByEmail;
    }

    @Override
    @Transactional
    public List<BookingResponseDto> execute(Long barberShopId,List<CreateBookingDto> createBookingDto) {
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
        BarberShop barberShop = this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id " + barberShopId + " not found"));

        if(!user.getId().equals(barberShop.getOwner().getId())){
            throw  new DeniedAction("Only the owner of barber shop can create bookings");
        }

        List<Booking> bookings = createBookingDto.stream() //map all create booking dto in parallel
                                                    .parallel()
                                                    .map(dto->{
                                                        Booking booking = this.bookingMapper.toEntity(dto);
                                                        booking.setBarberShop(barberShop);
                                                        return booking;
                                                    })
                                                    .toList();

        List<Booking> savedBookings = this.bookingRepository.saveAll(bookings); //save all

        return savedBookings.stream() //map all booking saved to dto and return
                            .map(this.bookingMapper::toDto)
                            .toList();
    }
}
