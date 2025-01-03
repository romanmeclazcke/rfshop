package org.example.rfshop.Review.Application.CreateReviewUseCase;

import org.example.rfshop.Review.Domain.Dto.Request.CreateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;

public interface CreateReviewUseCase {
    ReviewResponseDto execute(Long barberShopId, CreateReviewDto createReviewDto);
}
