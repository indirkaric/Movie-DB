package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Genre;
import com.indir.moviesdb.dto.GenreDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);
    Genre toEntity(GenreDto genreDto);
    GenreDto toDto(Genre genre);
}
