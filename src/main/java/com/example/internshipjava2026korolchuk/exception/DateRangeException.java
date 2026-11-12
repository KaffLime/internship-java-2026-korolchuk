package com.example.internshipjava2026korolchuk.exception;

public class DateRangeException extends RuntimeException {

    public DateRangeException(String message) {
        super(message);
    }

    public DateRangeException(String message, Throwable ex) {
        super(message, ex);
    }
}
