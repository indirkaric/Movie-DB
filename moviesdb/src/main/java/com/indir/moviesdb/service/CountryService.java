package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.CountryDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CountryService {
    List<CountryDto> getCountries();
    CountryDto findById(Integer id);
    ResponseEntity<CountryDto> saveCountry(CountryDto countryDto);
    void deleteCountry(Integer id);
}
