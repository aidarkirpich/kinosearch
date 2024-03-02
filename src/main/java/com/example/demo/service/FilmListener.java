package com.example.demo.service;

import com.example.demo.model.Film;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmListener {

    private final FilmMailService filmMailService;

    @JmsListener(destination = "${jms.queue}")
    public void receiveFilmList(List<Film> films){
        Logger log = LoggerFactory.getLogger(FilmListener.class);
        log.info("Done");
        try {
            filmMailService.sendFilmsToMail(films);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
