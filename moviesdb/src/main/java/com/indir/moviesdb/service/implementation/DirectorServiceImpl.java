package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.Director;
import com.indir.moviesdb.dto.DirectorDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.DirectorMapper;
import com.indir.moviesdb.repository.DirectorRepository;
import com.indir.moviesdb.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public List<DirectorDto> getDirectors() {
        List<DirectorDto> directors;
        try{
            List<Director> tempDirectors = new ArrayList<>();
            directorRepository.findAll().iterator().forEachRemaining(tempDirectors::add);
            directors = tempDirectors.stream().map(directorMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return directors;
    }

    @Override
    public DirectorDto findById(Integer id) {
        Optional<Director> director = directorRepository.findById(id);
        if(!director.isPresent()){
            throw new NotFoundException(Constants.DIRECTOR_NOT_FOUND + id);
        }
        return directorMapper.toDto(director.get());
    }

    @Override
    public ResponseEntity<DirectorDto> saveDirector(DirectorDto directorDto) {
        Director tempDirector = directorRepository.save(directorMapper.toEntity(directorDto));
        DirectorDto director = directorMapper.toDto(tempDirector);
        return new ResponseEntity<DirectorDto>(director, HttpStatus.CREATED);
    }

    @Override
    public void deleteDirector(Integer id) {
        Director director = directorMapper.toEntity(findById(id));
        directorRepository.delete(director);
    }
}
