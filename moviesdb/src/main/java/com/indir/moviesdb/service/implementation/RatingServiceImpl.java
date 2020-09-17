package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.Rating;
import com.indir.moviesdb.dto.RatingDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.RatingMapper;
import com.indir.moviesdb.repository.*;
import com.indir.moviesdb.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ResponseEntity<RatingDto> saveRating(RatingDto ratingDto) {
        RatingDto rating;
        if(!movieRepository.findById(ratingDto.getRatingId().getMovie().getId()).isPresent())
            throw new NotFoundException(Constants.MOVIE_NOT_FOUND + ratingDto.getRatingId().getMovie().getId());

        if(!userRepository.findById(ratingDto.getRatingId().getUser().getId()).isPresent())
            throw new NotFoundException(Constants.USER_NOT_FOUND + ratingDto.getRatingId().getUser().getId());

        try {
                rating = ratingMapper.toDto(ratingRepository.save(ratingMapper.toEntity(ratingDto)));
        } catch (Exception e) {
                log.error(e.getMessage());
                throw e;
        }
        return new ResponseEntity<RatingDto>(rating, HttpStatus.CREATED);

    }

    @Override
    public List<RatingDto> getAllRatings() {
        List<RatingDto> ratings;
        try{
            List<Rating> tempRatings = new ArrayList<>();
            ratingRepository.findAll().iterator().forEachRemaining(tempRatings::add);
            ratings = tempRatings.stream().map(ratingMapper::toDto).collect(Collectors.toList());
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return ratings;
    }


}
