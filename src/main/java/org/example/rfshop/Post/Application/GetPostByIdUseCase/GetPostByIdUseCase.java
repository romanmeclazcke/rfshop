package org.example.rfshop.Post.Application.GetPostByIdUseCase;

import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;

public interface GetPostByIdUseCase {
    PostResponseDto execute(Long postId);
}
