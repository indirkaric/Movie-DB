package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.City;
import com.indir.moviesdb.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    City toEntity(CityDto cityDto);
    CityDto toDto(City city);
}
