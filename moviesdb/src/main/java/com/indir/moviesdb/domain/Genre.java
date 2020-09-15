package com.indir.moviesdb.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "genre")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",unique = true)
    private String name;

}
