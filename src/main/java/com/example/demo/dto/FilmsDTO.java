package com.example.demo.dto;

import com.example.demo.model.FilmApi;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class FilmsDTO {
    private Long total;
    private Long totalPages;
    private List<FilmApi> items;
}
