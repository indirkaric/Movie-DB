package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
