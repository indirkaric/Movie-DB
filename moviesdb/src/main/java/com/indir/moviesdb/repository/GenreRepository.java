package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
