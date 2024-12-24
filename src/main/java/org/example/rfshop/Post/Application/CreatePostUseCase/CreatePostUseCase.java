package org.example.rfshop.Post.Application.CreatePostUseCase;

import org.example.rfshop.Post.Domain.Dto.Request.CreatePostDto;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;
import org.example.rfshop.Cloudinary.infrastructure.Config.Exception.FailToUploadImage;

public interface CreatePostUseCase {
    PostResponseDto execute(Long barberId, CreatePostDto createPostDto) throws FailToUploadImage;
}
