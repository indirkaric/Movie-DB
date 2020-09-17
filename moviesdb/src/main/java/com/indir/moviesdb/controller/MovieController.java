package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.MovieDto;
import com.indir.moviesdb.repository.filter.MovieSearchFilter;
import com.indir.moviesdb.service.MovieService;
import com.indir.moviesdb.service.util.PaginationUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(MovieController.ROOT_PATH)
public class MovieController {

    private final MovieService movieService;
    public static final String ROOT_PATH = "/api/v1/movies";

    @ApiOperation(value = Constants.MOVIE_FILTER)
    @GetMapping("")
    public ResponseEntity<List<MovieDto>> getAllMovies(@Valid MovieSearchFilter filter, Pageable pageable){
        Page<MovieDto> movies = movieService.getMovies(filter,pageable);
        HttpHeaders headers = PaginationUtils.generatePaginationHttpHeaders(movies);
        return ResponseEntity.ok().headers(headers).body(movies.getContent());
    }

    @ApiOperation(value = Constants.FIND_MOVIE)
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto>getMovieById(@PathVariable Integer id){
        MovieDto movieDto = movieService.findById(id);
        return new ResponseEntity<MovieDto>(movieDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.DELETE_MOVIE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id){
            movieService.deleteMovie(id);
            return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @ApiOperation(value = Constants.SAVE_MOVIE)
    @PostMapping("")
    public ResponseEntity<MovieDto> saveMovie(@Valid @RequestBody MovieDto movieDto){
        return  movieService.saveMovie(movieDto);
    }

    @ApiOperation(value = Constants.FIND_MOVIE_BY_TITLE)
    @GetMapping("/title/{title}")
    public ResponseEntity<MovieDto> findMovieByTitle(@PathVariable String title){
        MovieDto movieDto = movieService.findMovieByTitle(title);
        return new ResponseEntity<MovieDto>(movieDto, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.MOVIES_WITH_ORDERS)
    @GetMapping("/orders")
    public ResponseEntity<List<String>> getMoviesWithOrders(){
        List<String> movies = movieService.getMoviesWithOrders();
        return new ResponseEntity<List<String>>(movies, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.MOVIE_ADD_DIRECTOR)
    @PostMapping("/{movieId}/director/{directorId}")
    public ResponseEntity<MovieDto> addDirector(@PathVariable Integer movieId, @PathVariable Integer directorId){
        return movieService.addDirector(movieId, directorId);
    }

    @ApiOperation(value = Constants.MOVIE_ADD_STAR)
    @PostMapping("/{movieId}/star/{starId}")
    public ResponseEntity<MovieDto> addStar(@PathVariable Integer movieId, @PathVariable Integer starId){
        return movieService.addStar(movieId, starId);
    }

    @ApiOperation(value = Constants.MOVIE_ADD_GENRE)
    @PostMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<MovieDto> addGenre(@PathVariable Integer movieId, @PathVariable Integer genreId){
        return movieService.addGenre(movieId, genreId);
    }
}
