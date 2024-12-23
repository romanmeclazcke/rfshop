package org.example.rfshop.Review.Infrastructure.Mapper;

import org.example.rfshop.Review.Domain.Dto.Request.CreateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(CreateReviewDto createReviewDto);
    ReviewResponseDto toDto(Review review);
}
