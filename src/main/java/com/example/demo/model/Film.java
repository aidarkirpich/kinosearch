package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "f1_0")

public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("nameOriginal")
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filmid")
    @JsonProperty("kinopoiskId")
    private Long filmId;

    @Column(name = "year")
    @JsonProperty("year")
    private int year;

    @Column(name = "rating")
    @JsonProperty("ratingImdb")
    private double rating;

    @Column(name = "discription")
    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", year=" + year + '\'' +
                ", rating=" + rating + '\'' +
                ", description='" + description;
    }
}
