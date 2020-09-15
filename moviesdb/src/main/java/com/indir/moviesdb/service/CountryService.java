package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.CountryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CountryService {
    List<CountryDto> getCountries();
    CountryDto findById(Integer id);
    ResponseEntity saveCountry(CountryDto countryDto, BindingResult result);
    void deleteCountry(Integer id);
}
