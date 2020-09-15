package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.GenreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();
    GenreDto findById(Integer id);
    ResponseEntity saveGenre(GenreDto genreDto, BindingResult result);
    void deleteGenre(Integer id);
}
