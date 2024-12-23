package org.example.rfshop.Review.Infrastructure.Controller;

import org.example.rfshop.Review.Application.CreateReviewUseCase.CreateReviewUseCase;
import org.example.rfshop.Review.Application.DeleteReviewUseCase.DeleteReviewUseCase;
import org.example.rfshop.Review.Application.GetReviewByBarberShopId.GetReviewByBarberShopId;
import org.example.rfshop.Review.Application.UpdateReviewUseCase.UpdateReviewUseCase;
import org.example.rfshop.Review.Domain.Dto.Request.CreateReviewDto;
import org.example.rfshop.Review.Domain.Dto.Request.UpdateReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final CreateReviewUseCase createReviewUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final GetReviewByBarberShopId getReviewByBarberShopId;

    @Autowired
    public ReviewController(CreateReviewUseCase createReviewUseCase, UpdateReviewUseCase updateReviewUseCase, DeleteReviewUseCase deleteReviewUseCase, GetReviewByBarberShopId getReviewByBarberShopId) {
        this.createReviewUseCase = createReviewUseCase;
        this.updateReviewUseCase = updateReviewUseCase;
        this.deleteReviewUseCase = deleteReviewUseCase;
        this.getReviewByBarberShopId = getReviewByBarberShopId;
    }

    @GetMapping("/barber-shop/{barberShopId}")
    public ResponseEntity<?> getReviewByBarberShopId(@PathVariable Long barberShopId) {
        return new ResponseEntity<>(getReviewByBarberShopId.execute(barberShopId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/barber-shop/{barberShopId}")
    public ResponseEntity<?> createReview(@PathVariable Long userId, @PathVariable Long barberShopId, @RequestBody CreateReviewDto createReviewDto) {
        return new ResponseEntity<>(createReviewUseCase.execute(userId, barberShopId, createReviewDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewDto updateReviewDto) {
        return new ResponseEntity<>(updateReviewUseCase.execute(reviewId, updateReviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        deleteReviewUseCase.execute(reviewId);
        return new ResponseEntity<>("Review Deleted",HttpStatus.OK);
    }
}
