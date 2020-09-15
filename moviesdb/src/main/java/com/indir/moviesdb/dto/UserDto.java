package com.indir.moviesdb.dto;


import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserDto {
    private Integer id;
    @NotBlank(message = "First name can not be blank")
    @Size(min = 2, max = 15,message = "First name must be between 2 and 15")
    private String firstName;
    @NotBlank(message = "Last name can not be blank")
    @Size(min = 2, max = 15,message = "Last name must be between 2 and 15")
    private String lastName;
    @NotBlank(message = "Username can not be blank")
    @Size(min = 2, max = 40,message = "username must be between 2 and 40 ")
    private String username;
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, message = "Minimal length for password is 8 char")
    private String password;
    private CityDto city;

}
