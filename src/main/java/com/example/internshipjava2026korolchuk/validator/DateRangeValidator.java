package com.example.internshipjava2026korolchuk.validator;

import com.example.internshipjava2026korolchuk.exception.DateRangeException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {

    private String startDateField;
    private String endDateField;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        startDateField = constraintAnnotation.startDate();
        endDateField = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        Object startDate = getValue(object, startDateField);
        Object endDate = getValue(object, endDateField);

        if (startDate instanceof LocalDate start && endDate instanceof LocalDate end) {
            return !end.isBefore(start);
        } else if (startDate instanceof LocalDateTime start && endDate instanceof LocalDateTime end) {
            return !end.isBefore(start);
        }

        throw new DateRangeException("DateRange: поля должны иметь тип LocalDate или LocalDateTime");
    }

    private Object getValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        Field field = null;
        Object value = null;

        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            value = field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DateRangeException("DateRange: не удалось получить поля дат", e);
        }

        if (value == null) {
            throw new DateRangeException("DateRange: не удалось получить поля дат");
        }

        return value;
    }
}
