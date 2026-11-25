package com.example.internshipjava2026korolchuk.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class JacksonConfig {

    @Bean
    JsonMapperBuilderCustomizer dateAndTimeFormatCustomizer(
            @Value("${app.format.date:dd.MM.yyyy}") String dateFormat,
            @Value("${app.format.datetime:dd.MM.yyyy HH:mm:ss}") String dateTimeFormat
    ) {
        return builder -> builder
                .withConfigOverride(LocalDate.class,
                        cfg -> cfg.setFormat(JsonFormat.Value.forPattern(dateFormat)))
                .withConfigOverride(LocalDateTime.class,
                        cfg -> cfg.setFormat(JsonFormat.Value.forPattern(dateTimeFormat)));
    }
}
