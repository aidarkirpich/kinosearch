package com.example.demo.service;

import com.example.demo.repository.FilmRepository;
import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ExchangeClient exchangeClient;

    public FilmService(FilmRepository filmRepository, ExchangeClient exchangeClient) {
        this.filmRepository = filmRepository;
        this.exchangeClient = exchangeClient;
    }

    public List<Film> getAllFilms(FilmParametersDTO filmParametersDTO) {
        return saveFilm(exchangeClient.getFilms(filmParametersDTO));
    }

    public List<Film> saveFilm(List<Film> films) {
        List<Film> existingFilms = filmRepository.findAll();
        List<Film> resultFilms = new ArrayList<>();

        for (Film film : films) {
            if (film.getName() != null) {

                boolean isDuplicate = false;

                for (Film existingFilm : existingFilms) {
                    if (film.getName() != null && film.getName().equals(existingFilm.getName())) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    filmRepository.save(film);
                    resultFilms.add(film);
                }

            }
        }

        return resultFilms;
    }

    public List<Film> filterFilms(List<Film> allFilms, FilmParametersDTO filmParameters) {
        List<Film> filteredFilms = new ArrayList<>();

        for (Film film : allFilms) {
            if (isFilmInRange(film, filmParameters) && isFilmContainsKeyword(film, filmParameters.getKeyword())) {
                filteredFilms.add(film);
            }
        }
        return filteredFilms;
    }

    private boolean isFilmInRange(Film film, FilmParametersDTO filmParameters) {
        return (filmParameters.getYearFrom() == null || film.getYear() >= filmParameters.getYearFrom()) &&
                (filmParameters.getYearTo() == null || film.getYear() <= filmParameters.getYearTo()) &&
                (filmParameters.getRatingFrom() == null || film.getRating() >= filmParameters.getRatingFrom()) &&
                (filmParameters.getRatingTo() == null || film.getRating() <= filmParameters.getRatingTo());
    }

    private boolean isFilmContainsKeyword(Film film, String keyword) {
        if (keyword != null) {
            return film.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    film.getDescription().toLowerCase().contains(keyword.toLowerCase());
        } else return true;
    }
}
