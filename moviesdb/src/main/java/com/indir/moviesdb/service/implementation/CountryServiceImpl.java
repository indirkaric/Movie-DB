package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.Country;
import com.indir.moviesdb.dto.CountryDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.CountryMapper;
import com.indir.moviesdb.repository.CountryRepository;
import com.indir.moviesdb.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getCountries() {
        List<CountryDto> countries;
        try{
            List<Country> tempCountries = new ArrayList<>();
            countryRepository.findAll().iterator().forEachRemaining(tempCountries::add);
            countries = tempCountries.stream().map(countryMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return countries;
    }

    @Override
    public CountryDto findById(Integer id) {
        Optional<Country> country = countryRepository.findById(id);
        if(!country.isPresent()){
            throw new NotFoundException(Constants.COUNTRY_NOT_FOUND + id);
        }
        return countryMapper.toDto(country.get());
    }

    @Override
    public ResponseEntity saveCountry(CountryDto countryDto, BindingResult result) {
        if(result.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Country tempCountry = countryRepository.save(countryMapper.toEntity(countryDto));
        CountryDto country = countryMapper.toDto(tempCountry);
        return new ResponseEntity<CountryDto>(country, HttpStatus.CREATED);
    }

    @Override
    public void deleteCountry(Integer id) {
        Country country = countryMapper.toEntity(findById(id));
        countryRepository.delete(country);
    }
}
