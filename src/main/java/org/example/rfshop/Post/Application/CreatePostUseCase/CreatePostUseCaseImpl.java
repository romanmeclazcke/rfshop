package org.example.rfshop.Post.Application.CreatePostUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.example.rfshop.Cloudinary.Application.UploadImageUseCase.UploadImageUseCase;
import org.example.rfshop.Cloudinary.Domain.Enum.FolderEnum;
import org.example.rfshop.Cloudinary.Domain.Response.ImageResponseDto;
import org.example.rfshop.Post.Domain.Dto.Request.CreatePostDto;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;
import org.example.rfshop.Post.Infrastructure.Mapper.PostMapper;
import org.example.rfshop.Post.Infrastructure.Model.Post;
import org.example.rfshop.Post.Infrastructure.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostUseCaseImpl implements CreatePostUseCase {

    private final PostRepository postRepository;
    private final UploadImageUseCase uploadImageUseCase;
    private final BarberShopRepository barberShopRepository;
    private final PostMapper postMapper;

    @Autowired
    public  CreatePostUseCaseImpl(PostRepository postRepository, UploadImageUseCase uploadImageUseCase, BarberShopRepository barberShopRepository, PostMapper postMaper) {
        this.postRepository = postRepository;
        this.uploadImageUseCase = uploadImageUseCase;
        this.barberShopRepository = barberShopRepository;
        this.postMapper = postMaper;
    }

    public PostResponseDto execute(Long barberId, CreatePostDto createPostDto) {
        BarberShop barberShop = barberShopRepository.findById(barberId).orElseThrow(() -> new EntityNotFoundException("Barber with id " + barberId + " not found"));
        ImageResponseDto imageResponseDto = uploadImageUseCase.execute(createPostDto.getImage(), FolderEnum.POST_PICTURE.toString());// Upload the image

        // Map the DTO to the Post entity
        Post post = postMapper.toEntity(createPostDto);
        post.setBarberShop(barberShop);
        post.setSecureUrl(imageResponseDto.getSecure_url());

        // Save the Post entity and return the response DTO
        return postMapper.toDto(postRepository.save(post));
    }
}
