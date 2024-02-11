package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
}
