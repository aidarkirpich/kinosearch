package com.example.demo.controller;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;
import com.example.demo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmService filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Film>> getAllFilmsFromApi(FilmParametersDTO filmParametersDTO) {
        return new ResponseEntity<List<Film>>(filmService.getAllFilms(filmParametersDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/getfilms")
    public Page<Film> getFilmsFromDB(@RequestParam(required = false) Integer yearFrom,
                                     @RequestParam(required = false) Integer yearTo,
                                     @RequestParam(required = false) Integer ratingFrom,
                                     @RequestParam(required = false) Integer ratingTo,
                                     @RequestParam(required = false) String keyword,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        FilmParametersDTO filmParametersDTO = new FilmParametersDTO();
        filmParametersDTO.setYearFrom(yearFrom);
        filmParametersDTO.setYearTo(yearTo);
        filmParametersDTO.setRatingFrom(ratingFrom);
        filmParametersDTO.setRatingTo(ratingTo);
        filmParametersDTO.setKeyword(keyword);

        List<Film> filmsinDB = filmRepository.findAll();
        List<Film> listFilms = filmService.filterFilms(filmsinDB, filmParametersDTO);
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listFilms.size());
        Page<Film> filmPage = new PageImpl<>(listFilms.subList(start, end), pageable, listFilms.size());

        return filmPage;
    }
}
