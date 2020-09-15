package com.indir.moviesdb.repository.filter.spec;

import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.repository.filter.UserSearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.*;

@AllArgsConstructor
public class UserSearchSpecification implements Specification<User> {
    private final transient UserSearchFilter filter;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getId() != null){
            final Predicate id = criteriaBuilder.equal(root.<Integer>get("id"),filter.getId());
            predicates.add(id);
        }
        if(filter.getUsername() != null){
            final Predicate username = criteriaBuilder.equal(root.<String>get("username"),filter.getUsername());
            predicates.add(username);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
