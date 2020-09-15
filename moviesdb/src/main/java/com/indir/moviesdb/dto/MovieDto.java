package com.indir.moviesdb.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.*;

@Data
public class MovieDto {
    private Integer id;
    @NotBlank(message = "Title can not be blank ")
    @Size(min = 2, max = 40,message = "Title must be between 2 and 40")
    private String title;
    @NotNull(message = "Year can not be blank")
    @Min(value = 1888,message = "Minimum year is 18888")
    private int year;
    private String imageUrl;
    @NotNull(message = "Runtime can not be blank")
    @Min(value = 30,message = "Minimum runtime is 30")
    private int runtime;
    @NotNull(message = "Rate can not be empty")
    @DecimalMin(value = "1.0",message = "Min rate is 1.0")
    @DecimalMax(value = "10.0",message = "Max rate is 10.0")
    private Float rating;
    private int votes;
    private CityDto city;
    public List<StarDto> stars = new ArrayList<>();
    public List<DirectorDto> directors = new ArrayList<>();
    public List<GenreDto> genres = new ArrayList<>();

}
