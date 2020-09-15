package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Rating;
import com.indir.moviesdb.dto.RatingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);
    Rating toEntity(RatingDto ratingDto);
    RatingDto toDto(Rating rating);
}
