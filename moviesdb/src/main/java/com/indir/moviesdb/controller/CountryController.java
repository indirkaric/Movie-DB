package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.CountryDto;
import com.indir.moviesdb.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(CountryController.ROOT_PATH)
public class CountryController {

    private final CountryService countryService;
    public static final String ROOT_PATH = "/api/v1/countries";

    @ApiOperation(value = Constants.GET_COUNTRIES)
    @GetMapping("")
    public ResponseEntity<List<CountryDto>> getAllCountries(){
        List<CountryDto> countries = countryService.getCountries();
        return new ResponseEntity<List<CountryDto>>(countries, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.FIND_COUNTRY)
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Integer id){
        CountryDto countryDto = countryService.findById(id);
        return  new ResponseEntity<CountryDto>(countryDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.DELETE_COUNTRY)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id){
            countryService.deleteCountry(id);
            return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @ApiOperation(value = Constants.SAVE_COUNTRY)
    @PostMapping("")
    public ResponseEntity<CountryDto> saveCountry(@Valid @RequestBody CountryDto countryDto){
        return countryService.saveCountry(countryDto);
    }
}
