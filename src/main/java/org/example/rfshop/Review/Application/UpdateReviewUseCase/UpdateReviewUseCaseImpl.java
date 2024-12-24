package org.example.rfshop.Review.Application.UpdateReviewUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Review.Domain.Dto.Request.UpdateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public UpdateReviewUseCaseImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }
    @Override
    public ReviewResponseDto execute(Long reviewId, UpdateReviewDto updateReviewDto) {
        Review review = this.reviewRepository.findById(reviewId).orElseThrow(()->new EntityNotFoundException("Review with id "+reviewId+" not found"));
        Optional.ofNullable(updateReviewDto.getReview()).ifPresent(review::setReview);
        Optional.ofNullable(updateReviewDto.getRating()).ifPresent(review::setRating);
        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }
}
