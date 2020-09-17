package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.MovieDto;
import com.indir.moviesdb.repository.filter.MovieSearchFilter;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MovieService {
    MovieDto findById(Integer id);
    ResponseEntity<MovieDto> saveMovie(MovieDto movieDto);
    void deleteMovie(Integer id);
    MovieDto findMovieByTitle(String title);
    List<String> getMoviesWithOrders();
    ResponseEntity<MovieDto> addDirector(Integer movieId, Integer directorId);
    ResponseEntity<MovieDto> addStar(Integer movieId,Integer starId);
    ResponseEntity<MovieDto> addGenre(Integer movieId,Integer genreId);
    Page<MovieDto> getMovies(MovieSearchFilter filter, Pageable pageable);
}
