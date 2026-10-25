package com.example.internshipjava2026korolchuk.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Переданы невалидные параметры пагинации или сортировки: "
                + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Переданы невалидные значения сущности: "
                + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>("Невалидное состояние сущности: "
                + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Сущность не найдена: "
                + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateRangeException.class)
    public ResponseEntity<String> handleDateRangeException(DateRangeException ex) {
        return new ResponseEntity<>("Ошибка использования аннотации @DateRange. "
                + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CannotUpdateTravelRequestWithStatusException.class)
    public ResponseEntity<String> handleCannotUpdateTravelRequestWithStatusException(
            CannotUpdateTravelRequestWithStatusException ex) {
        return new ResponseEntity<>("Отказано в доступе. "
                + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
