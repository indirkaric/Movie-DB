package com.indir.moviesdb.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class KeyAndNewPasswordDto {
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, message = "Minimal length for password is 8 char")
    private String password;
    @NotBlank(message = "Key can not be blank")
    private String key;
}
