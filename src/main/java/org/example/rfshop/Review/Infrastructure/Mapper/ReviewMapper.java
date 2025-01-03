package org.example.rfshop.Review.Infrastructure.Mapper;

import org.example.rfshop.Review.Domain.Dto.Request.CreateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Request.RaitingResponseDto;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.domain.Dto.Response.UserNameDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(CreateReviewDto createReviewDto);

    @Mapping(target = "user", expression = "java(toUserNameDto(review.getUser()))")
    ReviewResponseDto toDto(Review review);

    RaitingResponseDto toDto(Double rating);

    default UserNameDto toUserNameDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserNameDto(user.getId(),user.getName(), user.getLastName());
    }


}
