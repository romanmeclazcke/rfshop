package org.example.rfshop.Review.Application.GetRatingByBarberShopId;

import org.example.rfshop.Review.Domain.Dto.Request.RaitingResponseDto;

public interface GetRatingByBarberShopId {
        RaitingResponseDto execute(Long barberShopId);
}
