package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.RatingDto;
import com.indir.moviesdb.service.RatingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(RatingController.ROOT_PATH)
public class RatingController {
    private final RatingService ratingService;
    public static final String ROOT_PATH = "/api/v1/ratings";

    @ApiOperation(value = Constants.GET_RATINGS)
    @GetMapping("")
    public ResponseEntity<List<RatingDto>> getAllRatings(){
        List<RatingDto> ratings = ratingService.getAllRatings();
        return new ResponseEntity<List<RatingDto>>(ratings, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.SAVE_RATING)
    @PostMapping("")
    public ResponseEntity saveRating(@Valid @RequestBody RatingDto ratingDto, BindingResult result){
        return ratingService.saveRating(ratingDto, result);
    }

}
