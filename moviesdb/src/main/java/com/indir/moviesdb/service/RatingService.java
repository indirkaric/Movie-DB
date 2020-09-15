package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.RatingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface RatingService {
    ResponseEntity saveRating(RatingDto ratingDto, BindingResult result);
    List<RatingDto> getAllRatings();
}
