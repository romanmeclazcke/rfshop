package org.example.rfshop.Review.Application.GetReviewByBarberShopId;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Review.Domain.Dto.Response.ReviewResponseDto;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetReviewByBarberShopIdImpl implements GetReviewByBarberShopId {

    private final ReviewRepository  reviewRepository;
    private final ReviewMapper reviewMapper;
    private final BarberShopRepository barberShopRepository;

    public GetReviewByBarberShopIdImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, BarberShopRepository barberShopRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public List<ReviewResponseDto> execute(Long barberShopId) {
            this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id "+ barberShopId +" not found"));
            return this.reviewRepository.findByBarberShopId(barberShopId).stream().map(this.reviewMapper::toDto).toList();
    }
}
