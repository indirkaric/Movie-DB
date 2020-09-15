package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.RatingId;
import com.indir.moviesdb.dto.RatingIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingIdMapper {
    RatingIdMapper INSTANCE = Mappers.getMapper(RatingIdMapper.class);
    RatingId toEntity(RatingIdDto ratingIdDto);
    RatingIdDto toDto(RatingId ratingId);
}
