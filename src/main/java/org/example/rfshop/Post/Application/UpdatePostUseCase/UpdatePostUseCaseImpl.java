package org.example.rfshop.Post.Application.UpdatePostUseCase;

import org.example.rfshop.Post.Domain.Dto.Request.UpdatePostDto;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostUseCaseImpl implements UpdatePostUseCase {

    @Override
    public PostResponseDto execute(Long postId, UpdatePostDto updatePostDto) {
        return null;
    }
}
