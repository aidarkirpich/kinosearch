package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailPropertiesConfiguration {
    private String text;
    private String subject;
    private String from;
    private String to;
    private String host;
    private String password;
    private String SMTP_Port;
    private String mailSMTP_Host;
    private String mailSMTP_Port;
    private String mailSMTP_SSL;
    private String mailSMTP_Auth;
}
