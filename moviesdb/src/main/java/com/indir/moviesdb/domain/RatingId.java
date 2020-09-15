package com.indir.moviesdb.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class RatingId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
