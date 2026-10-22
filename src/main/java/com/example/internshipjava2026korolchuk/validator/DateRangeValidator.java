package com.example.internshipjava2026korolchuk.validator;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (object instanceof TravelRequest travelRequest) {
            startDate = travelRequest.getStartDate();
            endDate = travelRequest.getEndDate();
        } else if (object instanceof TravelRequestDto travelRequestDto) {
            startDate = travelRequestDto.startDate();
            endDate = travelRequestDto.endDate();
        }

        if (startDate == null || endDate == null) {
            return true;
        }

        return !endDate.isBefore(startDate);
    }
}
