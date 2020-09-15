package com.indir.moviesdb.dto;

import lombok.Data;

@Data
public class RatingIdDto {
    private MovieDto movie;
    private UserDto user;
}
