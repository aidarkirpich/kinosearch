package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films_table")
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "year")
    private int year;

    @Column(name = "rating")
    private double rating;

    @Column(name = "description")
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Genre> genres;
}
