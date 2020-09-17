package com.indir.moviesdb.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class CountryDto {
    private Integer id;
    @NotBlank
    @NotBlank(message = "name can not be empty")
    @Size(min = 3, max = 50,message = "Name must be between 3 and 50")
    private String name;

}
