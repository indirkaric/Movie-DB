package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dao.StarsWithMoviesDao;
import com.indir.moviesdb.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(StarsWithMoviesController.ROOT_PATH)
public class StarsWithMoviesController {

    private final StarsWithMoviesDao starsWithMoviesDao;
    public static final String ROOT_PATH = "/api/v1/stars-movies";

    @ApiOperation(value = Constants.STARS_WITH_MOVIES)
    @GetMapping("")
    public ResponseEntity<Map<String, List<StarsWithMoviesDto>>> getStarsAndTheirMovies(){
        Map<String, List<StarsWithMoviesDto>> resultMap = starsWithMoviesDao.getStarsWithMovies();;
            return ResponseEntity.ok(resultMap);
    }

    @ApiOperation(value = Constants.STAR_WITH_MOST_MOVIES)
    @GetMapping("/star/max-movies")
    public ResponseEntity<StarWithTheMostMoviesDto> getStarWithTheMostMovies(){
        StarWithTheMostMoviesDto star= starsWithMoviesDao.getStarWithTheMostMovies();
        return new ResponseEntity<StarWithTheMostMoviesDto>(star, HttpStatus.OK);
    }
}
