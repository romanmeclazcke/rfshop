package org.example.rfshop.Review.Application.DeleteReviewUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteReviewUseCaseImpl implements DeleteReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Autowired
    public DeleteReviewUseCaseImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void execute(Long reviewId) {
        this.reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review  with id "+ reviewId+" not found"));
        this.reviewRepository.deleteById(reviewId);
    }
}
