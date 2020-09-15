package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.Star;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface StarRepository extends CrudRepository<Star,Integer>,  JpaSpecificationExecutor<Star> {
}
