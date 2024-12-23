package org.example.rfshop.Review.Domain.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.User.Infrastructure.Model.User;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private String review;
    private String rating;
    private User user;
    private Date createdAt;
    private Date updateAt;
}
