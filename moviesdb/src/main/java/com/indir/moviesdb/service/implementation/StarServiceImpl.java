package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.Star;
import com.indir.moviesdb.dto.StarDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.StarMapper;
import com.indir.moviesdb.repository.StarRepository;
import com.indir.moviesdb.repository.filter.StarSearchFilter;
import com.indir.moviesdb.repository.filter.spec.StarSearchSpecification;
import com.indir.moviesdb.service.StarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;
    private final StarMapper starMapper;

    @Override
    public StarDto findById(Integer id) {
        Optional<Star> star = starRepository.findById(id);
        if(!star.isPresent()){
            throw new NotFoundException(Constants.STAR_NOT_FOUND + id);
        }
        return starMapper.toDto(star.get());
    }

    @Override
    public ResponseEntity<StarDto> saveStar(StarDto starDto) {
        Star tempStar = starRepository.save(starMapper.toEntity(starDto));
        StarDto star = starMapper.toDto(tempStar);
        return new ResponseEntity<StarDto>(star, HttpStatus.CREATED);
    }

    @Override
    public void deleteStar(Integer id) {
        Star star = starMapper.toEntity(findById(id));
        starRepository.delete(star);
    }

    @Override
    public Page<StarDto> getStars(StarSearchFilter filter, Pageable pageable) {
        Specification<Star> specification = new StarSearchSpecification(filter);
        return starRepository.findAll(specification, pageable).map(starMapper::toDto);
    }
}
