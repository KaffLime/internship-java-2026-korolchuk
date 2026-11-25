package com.example.internshipjava2026korolchuk.exception;

import com.example.internshipjava2026korolchuk.dto.ExceptionDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionDto exDto = new ExceptionDto(ex,
                "Переданы невалидные параметры пагинации или сортировки");

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Переданы невалидные значения сущности");

        String message = ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Ошибка валидации");

        exDto.setMessage(message);

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleConstraintViolationException(ConstraintViolationException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Невалидное состояние сущности");

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Сущность не найдена");

        return new ResponseEntity<>(exDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateRangeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleDateRangeException(DateRangeException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Ошибка использования аннотации @DateRange");

        return ResponseEntity
                .internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(CannotUpdateTravelRequestWithStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleCannotUpdateTravelRequestWithStatusException(
            CannotUpdateTravelRequestWithStatusException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Отказано в доступе изменения статуса заявки");

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(CannotDeleteEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleCannotDeleteEntityException(CannotDeleteEntityException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Отказано в доступе удаления сущности");

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(NotValidDtoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleNotValidDtoException(NotValidDtoException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Невалидное тело запроса");

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleConversionFailedException(ConversionFailedException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Неправильные настройки приложения");

        return ResponseEntity
                .internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Неверный формат тела запроса");
        Throwable cause = ex.getRootCause();

        if (cause instanceof InvalidFormatException formatEx) {
            Class<?> targetType = formatEx.getTargetType();

            if (targetType == LocalDate.class) {
                exDto.setMessage("Неверный формат даты");
            }
        } else if (cause instanceof DateTimeParseException) {
            exDto.setMessage("Неверный формат даты");
        }

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exDto);
    }
}
