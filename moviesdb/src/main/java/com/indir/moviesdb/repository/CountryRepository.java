package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Integer> {
}
