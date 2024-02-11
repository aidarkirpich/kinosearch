package com.example.demo.service;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.dto.FilmsDTO;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ExchangeClient {
    private final RestTemplate restTemplate;
    private String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films";
    private String key = "14602616-5f8e-43da-b399-baed78e26cde";

    public String urlBuilder(FilmParametersDTO filmsParameters) {
        String urlBuilder = UriComponentsBuilder.fromUriString(url)
                .queryParam("ratingFrom", filmsParameters.getRatingFrom())
                .queryParam("ratingTo", filmsParameters.getRatingTo())
                .queryParam("yearFrom", filmsParameters.getYearFrom())
                .queryParam("yearTo", filmsParameters.getYearTo())
                .queryParam("keyword", filmsParameters.getKeyword())
                .queryParam("page", filmsParameters.getPage())
                .build().toString();
        System.out.println(urlBuilder);
        return urlBuilder;
    }

    public HttpHeaders getFilmHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.set("X-API-KEY", key);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return header;
    }

    public List<Film> getFilms(FilmParametersDTO fpdto) {
        try {
            HttpEntity<FilmsDTO> httpEntity = new HttpEntity<>(getFilmHeader());
            ResponseEntity<FilmsDTO> response = restTemplate
                    .exchange(urlBuilder(fpdto), HttpMethod.GET, httpEntity, FilmsDTO.class);
            return response.getBody().getItems();
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
