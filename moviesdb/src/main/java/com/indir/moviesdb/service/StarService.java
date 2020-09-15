package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.StarDto;
import com.indir.moviesdb.repository.filter.StarSearchFilter;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


public interface StarService {
    StarDto findById(Integer id);
    ResponseEntity saveStar(StarDto starDto, BindingResult result);
    void deleteStar(Integer id);
    Page<StarDto> getStars(StarSearchFilter filter, Pageable pageable);
}
