package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.CityDto;
import com.indir.moviesdb.mapper.CityMapper;
import com.indir.moviesdb.service.CityService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(CityController.ROOT_PATH)
public class CityController {

    private final CityService cityService;
    public static final String ROOT_PATH = "/api/v1/cities";

    @ApiOperation(value = Constants.GET_CITIES)
    @GetMapping("")
    public ResponseEntity<List<CityDto>> getAllCities(){
        List<CityDto> cities = cityService.getCities();
        return new ResponseEntity<List<CityDto>>(cities, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.FIND_CITY)
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Integer id){
        CityDto cityDto = cityService.findById(id);
        return  new ResponseEntity<CityDto>(cityDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.FIND_CITIES_BY_NAME)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CityDto>> getCitiesByName(@PathVariable String name){
        List<CityDto> cities = cityService.findCitiesByName(name);
        return new ResponseEntity<List<CityDto>>(cities, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.DELETE_CITY)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id){
            cityService.deleteCity(id);
            return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @ApiOperation(value = Constants.SAVE_CITY)
    @PostMapping("")
    public ResponseEntity<CityDto> saveCity(@Valid @RequestBody CityDto cityDto){
        return cityService.saveCity(cityDto);
    }
}
