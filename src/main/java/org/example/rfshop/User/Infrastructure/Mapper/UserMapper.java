package org.example.rfshop.User.Infrastructure.Mapper;

import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.domain.Dto.Request.CreateUserDto;
import org.example.rfshop.User.domain.Dto.Response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserResponseDto toDto(User user);
    User toEntity(CreateUserDto createUserDto);
}
