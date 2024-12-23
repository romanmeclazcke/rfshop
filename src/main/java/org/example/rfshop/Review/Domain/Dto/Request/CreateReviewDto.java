package org.example.rfshop.Review.Domain.Dto.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewDto {

    @NotEmpty(message = "Review cannot be empty")
    private String review;

    @NotNull(message = "Rating cannot be null")
    private Long  rating;
}
