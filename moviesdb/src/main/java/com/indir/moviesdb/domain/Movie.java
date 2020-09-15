package com.indir.moviesdb.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "movie")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title",unique = true)
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "votes")
    private int votes;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    @JoinTable(name = "movie_star", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "star_id"))
    public List<Star> stars = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    public List<Director> directors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    public List<Genre> genres = new ArrayList<>();

}
