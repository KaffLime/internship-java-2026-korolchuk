package com.example.internshipjava2026korolchuk.exception;

public class CannotDeleteEntityException extends RuntimeException {

    public CannotDeleteEntityException(String message) {
        super(message);
    }
}
