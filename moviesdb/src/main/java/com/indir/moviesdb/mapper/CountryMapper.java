package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Country;
import com.indir.moviesdb.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    Country toEntity(CountryDto countryDto);
    CountryDto toDto(Country country);
}
