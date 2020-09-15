package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.CityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CityService {
    List<CityDto> getCities();
    CityDto findById(Integer id);
    ResponseEntity saveCity(CityDto cityDto, BindingResult result);
    void deleteCity(Integer id);
    List<CityDto> findCitiesByName(String name);
}
