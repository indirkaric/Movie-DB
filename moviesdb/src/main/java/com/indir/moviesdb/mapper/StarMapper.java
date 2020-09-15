package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Star;
import com.indir.moviesdb.dto.StarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StarMapper {
    StarMapper INSTANCE = Mappers.getMapper(StarMapper.class);
    Star toEntity(StarDto starDto);
    StarDto toDto(Star star);

}
