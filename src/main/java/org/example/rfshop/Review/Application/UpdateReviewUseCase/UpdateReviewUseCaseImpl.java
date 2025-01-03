package org.example.rfshop.Review.Application.UpdateReviewUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Review.Domain.Dto.Request.UpdateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.Utils.Exception.DeniedAction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    public UpdateReviewUseCaseImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public ReviewResponseDto execute(Long reviewId, UpdateReviewDto updateReviewDto) {
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
        Review review = this.reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review  with id " + reviewId + " not found"));

        if (user.getId().equals(review.getUser().getId())){
            throw new DeniedAction("Only the owner of review can delete it");
        }

        Optional.ofNullable(updateReviewDto.getReview()).ifPresent(review::setReview);
        Optional.ofNullable(updateReviewDto.getRating()).ifPresent(review::setRating);
        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }
}
