package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.DirectorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface DirectorService {
    List<DirectorDto> getDirectors();
    DirectorDto findById(Integer id);
    ResponseEntity saveDirector(DirectorDto directorDto, BindingResult result);
    void deleteDirector(Integer id);
}
