package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.City;
import com.indir.moviesdb.dto.CityDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.CityMapper;
import com.indir.moviesdb.repository.*;
import com.indir.moviesdb.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CountryRepository countryRepository;

    @Override
    public List<CityDto> getCities() {
        List<CityDto> cities;
        try{
            List<City> tempCities = new ArrayList<>();
            cityRepository.findAll().iterator().forEachRemaining(tempCities::add);
            cities = tempCities.stream().map(cityMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return cities;
    }

    @Override
    public CityDto findById(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()){
            throw new NotFoundException(Constants.CITY_NOT_FOUND + id);
        }
        return cityMapper.toDto(city.get());
    }

    @Override
    public ResponseEntity<CityDto> saveCity(CityDto cityDto) {
        CityDto city;

        if(countryRepository.findById(cityDto.getCountry().getId()).isPresent()){
                City tempCity = cityMapper.toEntity(cityDto);
                cityRepository.save(tempCity);
                city = cityMapper.toDto(tempCity);
                return new ResponseEntity<CityDto>(city, HttpStatus.CREATED);
        }
        else
            throw new NotFoundException(Constants.COUNTRY_NOT_FOUND + cityDto.getCountry().getId());
    }

    @Override
    public void deleteCity(Integer id) {
        City city = cityMapper.toEntity(findById(id));
        cityRepository.delete(city);
    }

    @Override
    public List<CityDto> findCitiesByName(String name) {
        List<CityDto> cities;
        try{
            List<City> tempCities = new ArrayList<>();
            cityRepository.findCitiesByName(name).iterator().forEachRemaining(tempCities::add);
            cities = tempCities.stream().map(cityMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return cities;
    }


}
