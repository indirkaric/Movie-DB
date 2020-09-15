package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.DirectorDto;
import com.indir.moviesdb.service.DirectorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(DirectorController.ROOT_PATH)
public class DirectorController {
    private final DirectorService directorService;
    public static final String ROOT_PATH = "/api/v1/directors";

    @ApiOperation(value = Constants.GET_DIRECTORS)
    @GetMapping("")
    public ResponseEntity<List<DirectorDto>> getAllDirectors(){
        List<DirectorDto> directors = directorService.getDirectors();
        return new ResponseEntity<List<DirectorDto>>(directors, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.FIND_DIRECTOR)
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable Integer id){
        DirectorDto directorDto = directorService.findById(id);
        return  new ResponseEntity<DirectorDto>(directorDto, HttpStatus.OK);
    }

    @ApiOperation(value = Constants.DELETE_DIRECTOR)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Integer id){
            directorService.deleteDirector(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = Constants.SAVE_DIRECTOR)
    @PostMapping("")
    public ResponseEntity saveDirector(@Valid @RequestBody DirectorDto directorDto, BindingResult result){
        return directorService.saveDirector(directorDto,result);
    }
}
