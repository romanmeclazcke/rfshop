package org.example.rfshop.Post.Application.GetPostByIdUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;
import org.example.rfshop.Post.Infrastructure.Mapper.PostMapper;
import org.example.rfshop.Post.Infrastructure.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostByIdUseCaseImpl implements  GetPostByIdUseCase{

    private final PostRepository postRepository;
    private final PostMapper postMapper;


    @Autowired
    public GetPostByIdUseCaseImpl(PostRepository postRepository, PostMapper postMaper) {
        this.postRepository = postRepository;
        this.postMapper = postMaper;
    }

    @Override
    public PostResponseDto execute(Long postId) {
        return this.postRepository.findById(postId)
                .map(this.postMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Post with id "+ postId+" not found"));
    }

}
