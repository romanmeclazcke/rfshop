package org.example.rfshop.Post.Application.GetPostByBarberShopIdUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;
import org.example.rfshop.Post.Infrastructure.Mapper.PostMapper;
import org.example.rfshop.Post.Infrastructure.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostByBarberShopIdUseCaseImpl  implements GetPostByBarberShopIdUseCase {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private  final BarberShopRepository barberShopRepository;


    public GetPostByBarberShopIdUseCaseImpl(PostRepository postRepository, PostMapper postMapper,BarberShopRepository barberShopRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.barberShopRepository = barberShopRepository;
    }


    @Override
    public List<PostResponseDto> execute(Long barberId) {
        this.barberShopRepository.findById(barberId).orElseThrow(()-> new EntityNotFoundException("BarberShop with id "+ barberId+" not found"));
        return this.postRepository.findAllByBarberShopId(barberId)
                .stream()
                .map(this.postMapper::toDto)
                .toList();
    }
}
