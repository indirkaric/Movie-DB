package com.indir.moviesdb.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class GenreDto {
    private Integer id;
    @NotBlank(message = "Genre can not be blank")
    @Size(min = 4, max = 15,message = "Name must be between 4 and 15")
    private String name;

}
