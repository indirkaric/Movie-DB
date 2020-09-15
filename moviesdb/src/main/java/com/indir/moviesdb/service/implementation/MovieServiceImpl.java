package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.*;
import com.indir.moviesdb.dto.MovieDto;
import com.indir.moviesdb.exception.*;
import com.indir.moviesdb.mapper.*;
import com.indir.moviesdb.repository.MovieRepository;
import com.indir.moviesdb.repository.filter.MovieSearchFilter;
import com.indir.moviesdb.repository.filter.spec.MovieSearchSpecification;
import com.indir.moviesdb.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final DirectorService directorService;
    private final StarService starService;
    private final GenreService genreService;
    private final GenreMapper genreMapper;
    private final DirectorMapper directorMapper;
    private final StarMapper starMapper;

    @Override
    public MovieDto findById(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(!movie.isPresent()){
            throw  new NotFoundException(Constants.MOVIE_NOT_FOUND + id);
        }
        return movieMapper.toDto(movie.get());
    }

    @Override
    public ResponseEntity saveMovie(MovieDto movieDto,BindingResult result) {
        if(result.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Movie tempMovie = movieRepository.save(movieMapper.toEntity(movieDto));
        MovieDto movie = movieMapper.toDto(tempMovie);
        return new ResponseEntity<MovieDto>(movie,HttpStatus.CREATED);
    }

    @Override
    public void deleteMovie(Integer id) {
        Movie movie = movieMapper.toEntity(findById(id));
        movieRepository.delete(movie);
    }

    @Override
    public MovieDto findMovieByTitle(String title) {
        Optional<Movie> movie = Optional.ofNullable(movieRepository.findMovieByTitle(title));
        if(!movie.isPresent()){
            throw new NotFoundException(Constants.MOVIE_TITLE_NOT_FOUND + title);
        }
        return movieMapper.toDto(movie.get());
    }

    @Override
    public List<String> getMoviesWithOrders() {
        List<String> movies = new ArrayList<>();
        try{
            movieRepository.getMoviesWithOrders().iterator().forEachRemaining(movies::add);
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return movies;
    }

    @Override
    public ResponseEntity<MovieDto> addDirector(Integer movieId, Integer directorId) {
        Director director = directorMapper.toEntity(directorService.findById(directorId));
        Movie movie = movieMapper.toEntity(findById(movieId));
            if(movie.getDirectors().contains(director)){
                throw new DuplicateFoundException(Constants.DUPLICATE_VALUE);
            }else {
                movie.getDirectors().add(director);
                MovieDto movieDto  = movieMapper.toDto(movieRepository.save(movie));
                return new ResponseEntity<MovieDto>(movieDto,HttpStatus.OK);
            }
    }

    @Override
    public ResponseEntity<MovieDto> addStar(Integer movieId, Integer starId) {
        Star star = starMapper.toEntity(starService.findById(starId));
        Movie movie = movieMapper.toEntity(findById(movieId));
            if(movie.getStars().contains(star)){
                throw new DuplicateFoundException(Constants.DUPLICATE_VALUE);
            }else {
                movie.getStars().add(star);
                MovieDto movieDto = movieMapper.toDto(movieRepository.save(movie));
                return new ResponseEntity<MovieDto>(movieDto,HttpStatus.OK);
            }
    }

    @Override
    public ResponseEntity<MovieDto> addGenre(Integer movieId, Integer genreId) {
        Genre genre = genreMapper.toEntity(genreService.findById(genreId));
        Movie movie = movieMapper.toEntity(findById(movieId));
            if(movie.getGenres().contains(genre)){
                throw new DuplicateFoundException(Constants.DUPLICATE_VALUE);
            }else {
                movie.getGenres().add(genre);
                MovieDto movieDto = movieMapper.toDto(movieRepository.save(movie));
                return new ResponseEntity<MovieDto>(movieDto, HttpStatus.OK);
            }
        }

    @Override
    public Page<MovieDto> getMovies(MovieSearchFilter filter, Pageable pageable) {
        Specification<Movie> specification = new MovieSearchSpecification(filter);
        return movieRepository.findAll(specification, pageable).map(movieMapper::toDto);
    }

}
