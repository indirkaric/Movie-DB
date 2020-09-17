package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.GenreDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();
    GenreDto findById(Integer id);
    ResponseEntity<GenreDto> saveGenre(GenreDto genreDto);
    void deleteGenre(Integer id);
}
