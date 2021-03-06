package com.indir.moviesdb.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "director")
@Data
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
