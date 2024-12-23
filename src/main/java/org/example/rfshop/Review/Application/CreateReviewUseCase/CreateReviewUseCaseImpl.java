package org.example.rfshop.Review.Application.CreateReviewUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Review.Domain.Dto.Request.CreateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateReviewUseCaseImpl implements CreateReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final BarberShopRepository barberShopRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreateReviewUseCaseImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, BarberShopRepository barberShopRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.barberShopRepository = barberShopRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewResponseDto execute(Long userId, Long barberShopId, CreateReviewDto createReviewDto) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
        BarberShop barberShop = this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id " + barberShopId + " not found"));
        Review review = this.reviewMapper.toEntity(createReviewDto);
        review.setUser(user);
        review.setBarberShop(barberShop);
        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }
}