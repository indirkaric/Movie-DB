package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}
