package com.example.demo.service;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FilmDBService {

    private final FilmRepository filmRepository;

    public List<Film> exportFilmsFromDataBaseByParameters(FilmParametersDTO filmParameters, Pageable pageable) {

        Integer ratingFrom = filmParameters.getRatingFrom();
        Integer ratingTo = filmParameters.getRatingTo();
        Integer yearFrom = filmParameters.getYearFrom();
        Integer yearTo = filmParameters.getYearTo();
        String keyword = filmParameters.getKeyword();
        List<String> genres = filmParameters.getGenres();

        if (ratingFrom == null) {ratingFrom = 0;}
        if (ratingTo == null) {ratingTo = 10;}
        if (yearFrom == null) {yearFrom = 1000;}
        if (yearTo == null) {yearTo = 3000;}
        if (keyword == null) {keyword = "";}

        return filmRepository.findByParameters(ratingFrom, ratingTo, yearFrom, yearTo, keyword, genres, pageable);
    }

}
