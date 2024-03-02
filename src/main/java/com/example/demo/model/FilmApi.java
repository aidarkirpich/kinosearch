package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmApi implements Serializable {

    private Long id;

    @JsonProperty("nameOriginal")
    private String name;

    @JsonProperty("kinopoiskId")
    private Long filmId;

    @JsonProperty("year")
    private int year;

    @JsonProperty("ratingImdb")
    private double rating;

    @JsonProperty("description")
    private String description;

    @JsonProperty("genres")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Genre> genres;

}
