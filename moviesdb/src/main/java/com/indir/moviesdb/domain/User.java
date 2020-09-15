package com.indir.moviesdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnore
    @Column(name = "reset_key", unique = true)
    private String resetKey;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reset_date")
    private Date resetDate;

}
