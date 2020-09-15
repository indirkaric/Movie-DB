package com.indir.moviesdb.dto;

import lombok.Data;

@Data
public class UserAvgRateDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer cityId;
    private String username;
    private Double avgRate;
}
