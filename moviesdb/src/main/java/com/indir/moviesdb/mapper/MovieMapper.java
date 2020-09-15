package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Movie;
import com.indir.moviesdb.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
    Movie toEntity(MovieDto movieDto);
    MovieDto toDto(Movie movie);
}
