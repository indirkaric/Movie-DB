package com.indir.moviesdb.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class CityDto {
    private Integer id;
    @NotBlank(message = "name can not be empty")
    @Size(min = 3, max = 50,message = "Name must be between 3 and 50")
    private String name;
    @NotNull(message = "Country can not be empty")
    private CountryDto country;

}
