package org.example.rfshop.Review.Application.CreateReviewUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContextImpl;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Review.Domain.Dto.Request.CreateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreateReviewUseCaseImpl implements CreateReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final BarberShopRepository barberShopRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public CreateReviewUseCaseImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, BarberShopRepository barberShopRepository, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.barberShopRepository = barberShopRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public ReviewResponseDto execute(Long barberShopId, CreateReviewDto createReviewDto) {
        User currentUser = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
        BarberShop barberShop = this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id " + barberShopId + " not found"));

        Review review = this.reviewMapper.toEntity(createReviewDto);
        review.setUser(currentUser);
        review.setBarberShop(barberShop);

        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }
}