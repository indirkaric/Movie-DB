package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.Genre;
import com.indir.moviesdb.dto.GenreDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.GenreMapper;
import com.indir.moviesdb.repository.GenreRepository;
import com.indir.moviesdb.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAllGenres() {
        List<GenreDto> genres;
        try{
            List<Genre> tempGenres = new ArrayList<>();
            genreRepository.findAll().iterator().forEachRemaining(tempGenres::add);
            genres = tempGenres.stream().map(genreMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return genres;
    }

    @Override
    public GenreDto findById(Integer id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if(!genre.isPresent()){
            throw new NotFoundException(Constants.GENRE_NOT_FOUND + id);
        }
        return genreMapper.toDto(genre.get());
    }

    @Override
    public ResponseEntity saveGenre(GenreDto genreDto, BindingResult result) {
        if(result.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Genre tempGenre = genreRepository.save(genreMapper.toEntity(genreDto));
        GenreDto genre = genreMapper.toDto(tempGenre);
        return new ResponseEntity<GenreDto>(genre, HttpStatus.CREATED);

    }

    @Override
    public void deleteGenre(Integer id) {
        Genre genre = genreMapper.toEntity(findById(id));
        genreRepository.delete(genre);
    }
}
