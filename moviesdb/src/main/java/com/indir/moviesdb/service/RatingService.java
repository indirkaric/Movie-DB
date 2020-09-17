package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.RatingDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RatingService {
    ResponseEntity<RatingDto> saveRating(RatingDto ratingDto);
    List<RatingDto> getAllRatings();
}
