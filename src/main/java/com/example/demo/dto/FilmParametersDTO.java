package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FilmParametersDTO {
    private Integer ratingFrom;
    private Integer ratingTo;
    private Integer yearFrom;
    private Integer yearTo;
    private String keyword;
    private Integer page;
    private List<String> genres;
}
