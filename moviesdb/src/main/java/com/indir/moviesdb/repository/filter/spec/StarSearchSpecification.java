package com.indir.moviesdb.repository.filter.spec;

import com.indir.moviesdb.domain.*;
import com.indir.moviesdb.repository.filter.StarSearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.*;

@AllArgsConstructor
public class StarSearchSpecification implements Specification<Star> {
    private final transient StarSearchFilter filter;

    @Override
    public Predicate toPredicate(Root<Star> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getId() != null){
            final Predicate id = criteriaBuilder.equal(root.<Integer> get("id"), filter.getId());
            predicates.add(id);
        }
        if(filter.getFirstName() != null){
            final Predicate firstName = criteriaBuilder.equal(root.<String> get("firstName"), filter.getFirstName());
            predicates.add(firstName);
        }
        if(filter.getLastName() != null){
            final Predicate lastName = criteriaBuilder.equal(root.<String> get("lastName"), filter.getLastName());
            predicates.add(lastName);
        }
        if(filter.getCityName() != null){
            Join<Star, City> cities = root.join("city");
            predicates.add(criteriaBuilder.equal(cities.get("name"), filter.getCityName()));
        }
        if(filter.getCountryName() != null){
            Join<Star, City> countries = root.join("city").join("country");
            predicates.add(criteriaBuilder.equal(countries.get("name"), filter.getCountryName()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
