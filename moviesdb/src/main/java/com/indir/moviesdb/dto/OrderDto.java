package com.indir.moviesdb.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Integer id;
    private Date orderDate = new Date();
    private MovieDto movie;
    private UserDto user;
}
