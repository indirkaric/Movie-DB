package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.StarDto;
import com.indir.moviesdb.repository.filter.StarSearchFilter;
import com.indir.moviesdb.service.StarService;
import com.indir.moviesdb.service.util.PaginationUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(StarController.ROOT_PATH)
public class StarController {

    private final StarService starService;
    public static final String ROOT_PATH = "/api/v1/stars";

    @ApiOperation(value = Constants.STAR_FILTER)
    @GetMapping("")
    public ResponseEntity<List<StarDto>> getAllStars(@Valid StarSearchFilter filter, Pageable pageable){
        Page<StarDto> stars = starService.getStars(filter, pageable);
        HttpHeaders headers = PaginationUtils.generatePaginationHttpHeaders(stars);
        return ResponseEntity.ok().headers(headers).body(stars.getContent());
    }

    @ApiOperation(value = Constants.FIND_STAR)
    @GetMapping("/{id}")
    public ResponseEntity<StarDto> getStarById(@PathVariable Integer id){
        StarDto starDto = starService.findById(id);
        return new ResponseEntity<StarDto>(starDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.DELETE_STAR)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStar(@PathVariable Integer id){
            starService.deleteStar(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = Constants.SAVE_STAR)
    @PostMapping("")
    public ResponseEntity<StarDto> saveStar(@Valid @RequestBody StarDto starDto){
        return starService.saveStar(starDto);
    }

}
