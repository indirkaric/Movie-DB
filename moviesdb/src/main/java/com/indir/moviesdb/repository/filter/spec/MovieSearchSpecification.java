package com.indir.moviesdb.repository.filter.spec;


import com.indir.moviesdb.domain.*;
import com.indir.moviesdb.repository.filter.MovieSearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.*;

@AllArgsConstructor
public class MovieSearchSpecification implements Specification<Movie> {
    private final transient MovieSearchFilter filter;

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getTitle() != null){
            final Predicate title = criteriaBuilder.equal(root.<String>get("title"),filter.getTitle());
            predicates.add(title);
        }
        if(filter.getStarFirstName() != null){
            Join<Movie, Star> stars = root.join("stars");
            predicates.add(criteriaBuilder.equal(stars.get("firstName"),filter.getStarFirstName()));
        }
        if(filter.getGenre() != null){
            Join<Movie, Genre> genres = root.join("genres");
            predicates.add(criteriaBuilder.equal(genres.get("name"),filter.getGenre()));
        }
        if(filter.getYear() != null){
            final Predicate year = criteriaBuilder.equal(root.<Integer>get("year"),filter.getYear());
            predicates.add(year);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
