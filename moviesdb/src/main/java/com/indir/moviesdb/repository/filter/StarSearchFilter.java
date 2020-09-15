package com.indir.moviesdb.repository.filter;

import lombok.Data;

@Data
public class StarSearchFilter {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cityName;
    private String countryName;
}
