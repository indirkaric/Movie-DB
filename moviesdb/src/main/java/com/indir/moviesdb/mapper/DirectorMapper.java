package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Director;
import com.indir.moviesdb.dto.DirectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);
    Director toEntity(DirectorDto directorDto);
    DirectorDto toDto(Director director);
}
