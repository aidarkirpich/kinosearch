package com.example.demo.service;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class FilmScheduler {

    private final FilmDBService filmDBService;

    private final FilmMailService filmMailService;

    @Scheduled(cron = "0 * * * * ?")
    public void sendFilmsByGenre() {
        Pageable pageable =  Pageable.ofSize(20);
        List<Film> films = filmDBService.exportFilmsFromDataBaseByParameters
                (setParameters(0,10, 0,3000,""), pageable);
        filmMailService.sendFilmsToArtemis(films);
    }

    private FilmParametersDTO setParameters(Integer ratingFrom, Integer ratingTo, Integer yearFrom,
                                           Integer yearTo, String keyword) {
        FilmParametersDTO filmParametersDTO = new FilmParametersDTO();

        filmParametersDTO.setRatingFrom(ratingFrom);
        filmParametersDTO.setRatingTo(ratingTo);
        filmParametersDTO.setYearFrom(yearFrom);
        filmParametersDTO.setYearTo(yearTo);
        filmParametersDTO.setKeyword(keyword);
        filmParametersDTO.setGenres(getGenreByDay (java.time.LocalDate.now().getDayOfWeek().getValue()) );

        return filmParametersDTO;
    }

    private List<String> getGenreByDay(int day) {
        String genre = new String();
        switch (day) {
            case 1: genre = "комедия"; break;
            case 2: genre = "мелодрама"; break;
            case 3: genre = "фэнтези"; break;
            case 4: genre = "фантастика"; break;
            case 5: genre = "приключения"; break;
            case 6: genre = "музыка"; break;
            case 7: genre = "мультфильм"; break;
        }
        List<String> genres = new ArrayList<>();
        genres.add(genre);
        return genres;
    }

}
