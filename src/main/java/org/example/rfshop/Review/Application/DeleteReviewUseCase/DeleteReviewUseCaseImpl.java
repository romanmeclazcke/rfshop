package org.example.rfshop.Review.Application.DeleteReviewUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.Utils.Exception.DeniedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeleteReviewUseCaseImpl implements DeleteReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;

    @Autowired
    public DeleteReviewUseCaseImpl(ReviewRepository reviewRepository, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext) {
        this.reviewRepository = reviewRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
    }

    @Override
    public void execute(Long reviewId) {
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
        Review review = this.reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review  with id " + reviewId + " not found"));

        if (user.getId().equals(review.getUser().getId())){
            throw new DeniedAction("Only the owner of review can delete it");
        }

        this.reviewRepository.deleteById(reviewId);
    }
}
