package com.example.demo;

import com.example.demo.dto.FilmParametersDTO;
import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;
import com.example.demo.service.FilmService;
import com.mysql.cj.protocol.x.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
