package com.indir.moviesdb.domain;

import lombok.Data;
import javax.persistence.*;


@Entity
@Table(name = "rating")
@Data
public class Rating {
    @EmbeddedId
    private RatingId ratingId;

    @Column(name = "rating")
    private Float rating;

}
