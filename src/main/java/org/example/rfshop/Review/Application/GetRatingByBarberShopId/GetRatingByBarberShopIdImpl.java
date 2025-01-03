package org.example.rfshop.Review.Application.GetRatingByBarberShopId;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Review.Domain.Dto.Request.RaitingResponseDto;
import org.example.rfshop.Review.Infrastructure.Mapper.ReviewMapper;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetRatingByBarberShopIdImpl implements GetRatingByBarberShopId{

    private  final ReviewRepository reviewRepository;
    private  final BarberShopRepository barberShopRepository;
    private  final ReviewMapper reviewMapper;

    @Autowired
    public GetRatingByBarberShopIdImpl(ReviewRepository reviewRepository, BarberShopRepository barberShopRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.barberShopRepository = barberShopRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public RaitingResponseDto execute(Long barberShopId) {
        this.barberShopRepository.findById(barberShopId).orElseThrow(() -> new EntityNotFoundException("BarberShop with id "+ barberShopId +" not found"));
        Double rating = this.reviewRepository.getRatingByBarberShopId(barberShopId);
        return this.reviewMapper.toDto(rating);
    }
}
