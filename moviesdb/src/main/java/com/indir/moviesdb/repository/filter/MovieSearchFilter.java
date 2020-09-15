package com.indir.moviesdb.repository.filter;

import lombok.Data;

@Data
public class MovieSearchFilter {
    private String title;
    private String starFirstName;
    private String starLastName;
    private String genre;
    private Integer year;

}
