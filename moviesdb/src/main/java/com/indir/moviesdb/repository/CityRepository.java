package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {
    @Query("Select c from City c where c.name = :name")
    List<City> findCitiesByName(@Param("name") String name);
}
