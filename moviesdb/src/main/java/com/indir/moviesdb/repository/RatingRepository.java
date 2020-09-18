package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
}
