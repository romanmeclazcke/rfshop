package org.example.rfshop.Booking.Application.ReserveBookingUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Booking.Domain.Response.BookingResponseDto;
import org.example.rfshop.Booking.Infrastructure.Exception.BookingAlreadyReserved;
import org.example.rfshop.Booking.Infrastructure.Mapper.BookingMapper;
import org.example.rfshop.Booking.Infrastructure.Model.Booking;
import org.example.rfshop.Booking.Infrastructure.Repository.BookingRepository;
import org.example.rfshop.Email.Application.SendEmailUseCase.SendEmailUseCase;
import org.example.rfshop.Email.Domain.EmailTypes.BookingReserved;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.concurrent.CompletableFuture;

@Service
public class ReserveBookingUseCaseImpl implements ReserveBookingUseCase{
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;
    private final GetUserByEmail getUserByEmail;
    private final SendEmailUseCase sendEmailUseCase;

    @Autowired
    public ReserveBookingUseCaseImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext, GetUserByEmail getUserByEmail, SendEmailUseCase sendEmailUseCase) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
        this.getUserByEmail = getUserByEmail;
        this.sendEmailUseCase = sendEmailUseCase;
    }

    @Override
    public BookingResponseDto execute(Long bookingId) {
        Booking bookingToReserve = this.bookingRepository.findById(bookingId).orElseThrow(()-> new EntityNotFoundException("Booking with id "+ bookingId + " not found"));
        User currentUser = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));

        if(bookingToReserve.getReserved()){
            throw  new BookingAlreadyReserved("Booking already reserved");
        }

        bookingToReserve.setReserved(true);
        bookingToReserve.setUser(currentUser);
        Booking bookingReserved = this.bookingRepository.save(bookingToReserve);

        CompletableFuture.runAsync(()->{
            try {
                this.sendEmailUseCase.execute(new BookingReserved(currentUser.getEmail(), "Booking Reserved", bookingReserved)); //TODO:check this because I don't receive an email
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        return this.bookingMapper.toDto(bookingReserved);
    }
}
