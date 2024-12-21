package org.example.rfshop.Post.Application.GetPostByBarberShopIdUseCase;

import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;

import java.util.List;

public interface GetPostByBarberShopIdUseCase {
    List<PostResponseDto> execute(Long barberId);
}
