package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.DirectorDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DirectorService {
    List<DirectorDto> getDirectors();
    DirectorDto findById(Integer id);
    ResponseEntity<DirectorDto> saveDirector(DirectorDto directorDto);
    void deleteDirector(Integer id);
}
