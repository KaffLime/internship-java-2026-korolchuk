package com.example.internshipjava2026korolchuk.exception;

import com.example.internshipjava2026korolchuk.dto.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionDto exDto = new ExceptionDto(ex,
                "Переданы невалидные параметры пагинации или сортировки");

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Переданы невалидные значения сущности");

        String message = ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Ошибка валидации");

        exDto.setMessage(message);

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> handleConstraintViolationException(ConstraintViolationException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Невалидное состояние сущности");

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Сущность не найдена");

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateRangeException.class)
    public ResponseEntity<ExceptionDto> handleDateRangeException(DateRangeException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Ошибка использования аннотации @DateRange");

        return new ResponseEntity<>(exDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CannotUpdateTravelRequestWithStatusException.class)
    public ResponseEntity<ExceptionDto> handleCannotUpdateTravelRequestWithStatusException(
            CannotUpdateTravelRequestWithStatusException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Отказано в доступе изменения статуса заявки");

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotDeleteEntityException.class)
    public ResponseEntity<ExceptionDto> handleCannotDeleteEntityException(CannotDeleteEntityException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Отказано в доступе удаления сущности");

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidDtoException.class)
    public ResponseEntity<ExceptionDto> handleNotValidDtoException(NotValidDtoException ex) {
        ExceptionDto exDto = new ExceptionDto(ex, "Невалидное тело запроса");

        return new ResponseEntity<>(exDto, HttpStatus.BAD_REQUEST);
    }
}
