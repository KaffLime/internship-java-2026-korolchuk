package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class TravelRequestFilter {
    Employee employee;
    Department department;
    String destination;
    LocalDate afterStartDate;
    LocalDate beforeStartDate;
    LocalDate afterEndDate;
    LocalDate beforeEndDate;

    @Size(min = 10, max = 500, message = "Длина назначения должна составлять от 10 до 500 символов")
    String purpose;

    TravelRequestStatus status;

    @DecimalMin(value = "0.0", message = "Стоимость не может быть отрицательной")
    BigDecimal minEstimatedCost;

    @DecimalMin(value = "0.0", message = "Стоимость не может быть отрицательной")
    BigDecimal maxEstimatedCost;

    public static Specification<TravelRequest> byFilter(TravelRequestFilter filter) {
        return Specification.allOf(
                TravelRequestSpecs.forEmployee(filter.employee.getId()),
                TravelRequestSpecs.inDepartment(filter.department.getId()),
                TravelRequestSpecs.hasDestination(filter.getDestination()),
                TravelRequestSpecs.startingAfterDate(filter.getAfterStartDate()),
                TravelRequestSpecs.startingBeforeDate(filter.getBeforeStartDate()),
                TravelRequestSpecs.endingAfterDate(filter.getAfterEndDate()),
                TravelRequestSpecs.endingBeforeDate(filter.getBeforeEndDate()),
                TravelRequestSpecs.containsPurpose(filter.getPurpose()),
                TravelRequestSpecs.hasStatus(filter.getStatus()),
                TravelRequestSpecs.higherThanCost(filter.getMinEstimatedCost()),
                TravelRequestSpecs.lowerThanCost(filter.getMaxEstimatedCost())
        );
    }
}
