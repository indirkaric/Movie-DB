package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.*;
import com.indir.moviesdb.dto.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CityMapperTest {

    static final String COUNTRY_NAME = "Bosnia and Herzegovina";
    static final int COUNTRY_ID = 1;
    static final String CITY_NAME = "Tuzla";
    static final int CITY_ID = 12;

    @Test
    void TestToEntity() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(COUNTRY_ID);
        countryDto.setName(COUNTRY_NAME);

        CityDto cityDto = new CityDto();
        cityDto.setName(CITY_NAME);
        cityDto.setId(CITY_ID);
        cityDto.setCountry(countryDto);

        City city = Mappers.getMapper(CityMapper.class).toEntity(cityDto);

        assertEquals(CITY_NAME, city.getName());
        assertEquals(CITY_ID, city.getId());
        assertEquals(COUNTRY_NAME, city.getCountry().getName());

    }

    @Test
    void TestToDto() {
        Country country = new Country();
        country.setId(COUNTRY_ID);
        country.setName(COUNTRY_NAME);

        City city = new City();
        city.setId(CITY_ID);
        city.setName(CITY_NAME);
        city.setCountry(country);

        CityDto cityDto = Mappers.getMapper(CityMapper.class).toDto(city);

        assertEquals(CITY_NAME, cityDto.getName());
        assertEquals(CITY_ID, cityDto.getId());
        assertEquals(COUNTRY_NAME, cityDto.getCountry().getName());

    }
}