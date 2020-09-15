package com.indir.moviesdb.bootstrap;

import com.indir.moviesdb.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final StarRepository starRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;

    public DataLoader(MovieRepository movieRepository, StarRepository starRepository, DirectorRepository directorRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.starRepository = starRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
