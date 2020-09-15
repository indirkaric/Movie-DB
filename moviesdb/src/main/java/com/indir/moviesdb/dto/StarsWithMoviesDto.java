package com.indir.moviesdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StarsWithMoviesDto {
    private int year;
    private String title;
    private Float rating;
    @JsonIgnore
    private String name;
}
