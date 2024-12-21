package org.example.rfshop.Post.Infrastructure.Mapper;

import org.example.rfshop.Post.Domain.Dto.Request.CreatePostDto;
import org.example.rfshop.Post.Domain.Dto.Response.PostResponseDto;
import org.example.rfshop.Post.Infrastructure.Model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toEntity(CreatePostDto createPostDto);
    PostResponseDto toDto(Post post);
}
