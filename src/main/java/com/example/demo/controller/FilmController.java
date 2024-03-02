package com.example.demo.controller;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import com.example.demo.service.FilmDBService;
import com.example.demo.service.FilmMailService;
import com.example.demo.service.FilmScheduler;
import com.example.demo.service.FilmApiService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmController {

    private final FilmApiService filmApiService;

    private final FilmScheduler filmScheduler;

    private final FilmMailService filmMailService;

    private final FilmDBService filmDBService;

    @PostMapping(value = "/sendfilmstoqueue")
    public ResponseEntity<List<Film>> sendFilmsToQueue() {
        filmScheduler.sendFilmsByGenre();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sendfilmstomail", consumes = "application/json")
    public void sendFilmsToMail(@RequestBody List<Film> films) {
        try {
            filmMailService.sendFilmsToMail(films);
        } catch (MessagingException e) {
            System.out.println("Messaging Exception");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IO Exception");
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Film>> getAllFilmsFromApi(FilmParametersDTO filmParametersDTO) {
        return new ResponseEntity<>(filmApiService.checkFilmsNotExistedAndSaveAll(filmParametersDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/getfilms")
    public ResponseEntity<List<Film>> getFilmsFromDB(FilmParametersDTO filmParametersDTO, @RequestParam(required = false, defaultValue = "10") int pageSize) {

        Pageable pageable;
        if (pageSize <= 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = Pageable.ofSize(pageSize);
        }
        List<Film> filmList = filmDBService.exportFilmsFromDataBaseByParameters(filmParametersDTO, pageable);
        return ResponseEntity.ok(filmList);
    }

}
