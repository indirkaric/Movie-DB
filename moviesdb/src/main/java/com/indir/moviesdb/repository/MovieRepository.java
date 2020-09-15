package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie,Integer>, JpaSpecificationExecutor<Movie> {
    @Query(value = "Select * from movie where title = :title",nativeQuery = true)
    Movie findMovieByTitle(@Param("title")String title);

    @Query(value =  "Select " +
                    "m.title " +
                    "from movie as m " +
                    "inner join order_info on m.id = order_info.movie_id " +
                    "group by order_info.movie_id",
                    nativeQuery = true)
    List<String> getMoviesWithOrders();

}
