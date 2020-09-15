package com.indir.moviesdb.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class StarDto {
    private Integer id;
    @NotBlank(message = "First name can not be blank")
    @Size(min = 2, max = 15,message = "First name must be between 2 and 15")
    private String firstName;
    @NotBlank(message = "Last name can not be blank")
    @Size(min = 2, max = 15,message = "Last name must be between 2 and 15")
    private String lastName;
    private CityDto city;
}
