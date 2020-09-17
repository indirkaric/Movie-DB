package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.StarDto;
import com.indir.moviesdb.repository.filter.StarSearchFilter;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

public interface StarService {
    StarDto findById(Integer id);
    ResponseEntity<StarDto> saveStar(StarDto starDto);
    void deleteStar(Integer id);
    Page<StarDto> getStars(StarSearchFilter filter, Pageable pageable);
}
