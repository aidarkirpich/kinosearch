package com.example.demo.service;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.dto.FilmsDTO;
import com.example.demo.model.FilmApi;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExchangeClient {

    private final RestTemplate restTemplate;

    @Value("${rest.api.base-url}")
    private String url;

    @Value("${rest.api.base-key}")
    private String key;

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

    public List<FilmApi> getFilms(FilmParametersDTO fpdto) {
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
