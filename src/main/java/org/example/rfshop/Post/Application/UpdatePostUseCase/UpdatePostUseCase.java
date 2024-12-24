package org.example.rfshop.Post.Application.UpdatePostUseCase;

import org.example.rfshop.Post.Domain.Dto.Request.UpdatePostDto;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;

public interface UpdatePostUseCase  {
    PostResponseDto execute(Long postId, UpdatePostDto updatePostDto);
}
