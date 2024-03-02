package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.FilmApi;
import com.example.demo.repository.FilmRepository;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmApiService {

    private final FilmRepository filmRepository;
    private final ExchangeClient exchangeClient;
    private final ModelMapper modelMapper;


    public Film convertToFilm(FilmApi filmApi) {
        return modelMapper.map(filmApi, Film.class);
    }

    public List<Film> checkFilmsNotExistedAndSaveAll(FilmParametersDTO filmParametersDTO) {
        List<FilmApi> filmsFromApi = exchangeClient.getFilms(filmParametersDTO);
        List<Film> filteredFilmsFromApi = new ArrayList<>();

        for (FilmApi newFilm : filmsFromApi) {
            Film film = convertToFilm(newFilm);
            Film filmsWithSameName = filmRepository.findByName(newFilm.getName());
            if (filmsWithSameName == null && film.getName() != null) {
                filteredFilmsFromApi.add(film);
                filmRepository.save(film);
            }
        }

        return filteredFilmsFromApi;
    }

}







//    public List<FilmDto> checkFilmsNotExistedAndSaveAll(FilmParametersDTO filmParametersDTO) {
//
//        List<FilmApi> filmsFromApi = exchangeClient.getFilms(filmParametersDTO);
//        List<FilmDto> filteredFilmsFromApi = new ArrayList<>();
//
//        for (FilmApi newFilm : filmsFromApi) {
//
//            FilmDto filmsWithSameName = filmRepository.findByName(newFilm.getName());
//            if (filmsWithSameName == null && newFilm.getName() != null) {
//                filteredFilmsFromApi.add(newFilm);
//                filmRepository.save(newFilm);
//            }
//
//        }
//
//        return filteredFilmsFromApi;
//    }
