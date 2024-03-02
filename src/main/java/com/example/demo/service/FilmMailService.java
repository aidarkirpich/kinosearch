package com.example.demo.service;

import com.example.demo.config.MailPropertiesConfiguration;
import com.example.demo.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class FilmMailService {

    private final JmsTemplate jmsTemplate;
    private final MailPropertiesConfiguration mailPropertiesConfiguration;

    @Value("${jms.queue}")
    String jmsQueue;

    public void sendFilmsToArtemis(List<Film> films) {
        jmsTemplate.convertAndSend(jmsQueue, films);
    }

    public void sendFilmsToMail(List<Film> films) throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put(mailPropertiesConfiguration.getMailSMTP_Host(), mailPropertiesConfiguration.getHost());
        properties.put(mailPropertiesConfiguration.getMailSMTP_Port(), mailPropertiesConfiguration.getSMTP_Port());
        properties.put(mailPropertiesConfiguration.getMailSMTP_SSL(), "true");
        properties.put(mailPropertiesConfiguration.getMailSMTP_Auth(), "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailPropertiesConfiguration.getFrom(), mailPropertiesConfiguration.getPassword());
            }
        };
        Session session = Session.getInstance(properties, auth);

        session.setDebug(true);

        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(new String[]{"film_id", "film_name", "year", "rating", "description", "genres"});

        ObjectMapper objectMapper = new ObjectMapper();

        String genresJson;

        for (Film film : films) {
            try {

                genresJson = objectMapper.writeValueAsString(film.getGenres());
            } catch (JsonProcessingException e) {
                System.out.println("Ошибка при обработке фильма " + film.getName());
                throw new RuntimeException(e);
            }

            csvWriter.writeNext(new String[]{String.valueOf(film.getFilmId()), film.getName(),
                    String.valueOf(film.getYear()), String.valueOf(film.getRating()), film.getDescription(), genresJson});
        }

        try {
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка при закрытии CSVWriter");
            throw new RuntimeException(e);
        }

        String report = writer.toString();

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(mailPropertiesConfiguration.getFrom()));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailPropertiesConfiguration.getTo()));
        mimeMessage.setSubject(mailPropertiesConfiguration.getSubject());
        mimeMessage.setText(mailPropertiesConfiguration.getText());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(report.getBytes(), "text/csv")));
        mimeBodyPart.setFileName("film.csv");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mimeMessage.setContent(multipart);

        Transport.send(mimeMessage);

        writer.close();

    }

}
