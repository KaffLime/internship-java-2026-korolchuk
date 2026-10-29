package com.example.internshipjava2026korolchuk.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionDto {

    String message;
    String className;
    LocalDateTime dateTime;
    String defaultMessage = null;

    public ExceptionDto(Exception ex) {
        message = ex.getMessage();
        className = ex.getClass().getName();
        dateTime = LocalDateTime.now();
    }

    public ExceptionDto(Exception ex, String defaultMessage) {
        this(ex);
        this.defaultMessage = defaultMessage;
    }
}