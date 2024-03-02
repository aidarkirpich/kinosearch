package com.example.demo.service;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FIlmServiceTest {

    @InjectMocks
    private FilmApiService filmApiService;

    @InjectMocks
    private FilmDBService filmDBService;
    @Test
    public void testKinopoiskImport() {

    }

    @Test
    public void testCheckFilmsNotExistedAndSaveAll() {
        FilmParametersDTO filmParametersDTO = new FilmParametersDTO();
        filmParametersDTO.setYearFrom(1000);
        filmParametersDTO.setYearTo(3000);
        filmParametersDTO.setRatingFrom(1);
        filmParametersDTO.setRatingTo(10);
        List<Film> films = filmApiService.checkFilmsNotExistedAndSaveAll(filmParametersDTO);
        for (Film film : films) {
            System.out.println(film.getName());
        }
        Assertions.assertNotNull(films);
    }

    @Test
    public void testFilterFilms() {
        FilmParametersDTO filmParametersDTO = new FilmParametersDTO();
        filmParametersDTO.setYearFrom(1000);
        filmParametersDTO.setYearTo(3000);
        filmParametersDTO.setRatingFrom(1);
        filmParametersDTO.setRatingTo(10);
        Pageable pageable = Pageable.ofSize(20);
        List<Film> films = filmDBService.exportFilmsFromDataBaseByParameters(filmParametersDTO, pageable);
        for (Film film : films) {
            System.out.println(film.getName());
        }
        Assertions.assertNotNull(films);
    }

}
