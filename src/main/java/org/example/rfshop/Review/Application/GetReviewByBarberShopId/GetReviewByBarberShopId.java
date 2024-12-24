package org.example.rfshop.Review.Application.GetReviewByBarberShopId;

import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import java.util.List;

public interface GetReviewByBarberShopId {
    List<ReviewResponseDto> execute(Long barberShopId);
}
