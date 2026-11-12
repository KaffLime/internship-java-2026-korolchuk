package com.example.internshipjava2026korolchuk.exception;

public class CannotUpdateTravelRequestWithStatusException extends RuntimeException {

    public CannotUpdateTravelRequestWithStatusException(String message) {
        super(message);
    }
}
