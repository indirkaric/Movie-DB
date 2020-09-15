package com.indir.moviesdb.repository;

import com.indir.moviesdb.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface UserRepository extends CrudRepository<User,Integer>, JpaSpecificationExecutor<User> {
    @Query(value =  "Select " +
                    "u.id," +
                    "u.first_name," +
                    "u.last_name," +
                    "u.email," +
                    "u.city_id," +
                    "u.username " +
                    "from " +
                    "user as u " +
                    "inner join order_info as o on u.id = o.customer_id " +
                    "group by u.id",
                     nativeQuery = true)
    List<User> getUsersWithOrders();

    @Query(value =  "Select " +
                    "u.id," +
                    "u.first_name," +
                    "u.last_name," +
                    "u.username," +
                    "u.email," +
                    "u.city_id " +
                    "from " +
                    "user as u " +
                    "left join order_info as o on u.id = o.customer_id " +
                    "where o.customer_id is null " +
                    "group by u.id",
                     nativeQuery = true)
    List<User> getUsersWithoutOrders();
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByUsernameOrEmail(String username,String email);
    Optional<User> findByResetKey(String key);



}
