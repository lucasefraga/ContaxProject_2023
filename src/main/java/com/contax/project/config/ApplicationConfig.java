package com.contax.project.config;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@Configuration
public class ApplicationConfig {

    private final JdbcTemplate jdbcTemplate;

    public ApplicationConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Insert a couple of messages in the DB
    @PostConstruct
    public void init() {
        jdbcTemplate.update("INSERT INTO messages (title, content, creation_date, update_date) VALUES (?, ?, ?, ?)",
                "Message 1", "Content of message 1", LocalDateTime.now(), LocalDateTime.now());
        jdbcTemplate.update("INSERT INTO messages (title, content, creation_date, update_date) VALUES (?, ?, ?, ?)",
                "Message 2", "Content of message 2", LocalDateTime.now(), LocalDateTime.now());

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
