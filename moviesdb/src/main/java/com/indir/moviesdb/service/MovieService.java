package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.MovieDto;
import com.indir.moviesdb.repository.filter.MovieSearchFilter;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface MovieService {
    MovieDto findById(Integer id);
    ResponseEntity saveMovie(MovieDto movieDto,BindingResult result);
    void deleteMovie(Integer id);
    MovieDto findMovieByTitle(String title);
    List<String> getMoviesWithOrders();
    ResponseEntity<?> addDirector(Integer movieId, Integer directorId);
    ResponseEntity<?> addStar(Integer movieId,Integer starId);
    ResponseEntity<?> addGenre(Integer movieId,Integer genreId);
    Page<MovieDto> getMovies(MovieSearchFilter filter, Pageable pageable);
}
