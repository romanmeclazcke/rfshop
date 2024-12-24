package org.example.rfshop.Review.Application.UpdateReviewUseCase;

import org.example.rfshop.Review.Domain.Dto.Request.UpdateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;

public interface UpdateReviewUseCase {
    ReviewResponseDto execute(Long reviewId, UpdateReviewDto updateReviewDto);
}
