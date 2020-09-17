package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.GenreDto;
import com.indir.moviesdb.service.GenreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(GenreController.ROOT_PATH)
public class GenreController {

    private final GenreService genreService;
    public static final String ROOT_PATH = "/api/v1/genres";

    @ApiOperation(value = Constants.GET_GENRES)
    @GetMapping("")
    public ResponseEntity<List<GenreDto>> getAllGenres(){
        List<GenreDto> genres = genreService.getAllGenres();
        return new ResponseEntity<List<GenreDto>>(genres, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.FIND_GENRE)
    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Integer id){
        GenreDto genreDto = genreService.findById(id);
        return  new ResponseEntity<GenreDto>(genreDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.DELETE_GENRE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id){
        genreService.deleteGenre(id);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @ApiOperation(value = Constants.SAVE_GENRE)
    @PostMapping("")
    public ResponseEntity<GenreDto> saveGenre(@Valid @RequestBody GenreDto genreDto){
       return genreService.saveGenre(genreDto);
    }
}
