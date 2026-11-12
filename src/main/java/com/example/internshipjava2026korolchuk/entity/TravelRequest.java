package com.example.internshipjava2026korolchuk.entity;

import com.example.internshipjava2026korolchuk.validator.DateRange;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DateRange
public class TravelRequest {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ToString.Exclude
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ToString.Exclude
    Department department;

    @Column(name = "destination")
    String destination;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "purpose")
    @Size(min = 10, max = 500, message = "Длина назначения должна составлять от 10 до 500 символов")
    String purpose;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    TravelRequestStatus status = TravelRequestStatus.DRAFT;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    @ColumnDefault("now()")
    LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @ColumnDefault("now()")
    LocalDateTime updatedAt;

    @Column(name = "estimated_cost")
    @DecimalMin(value = "0.0", message = "Стоимость не может быть отрицательной")
    BigDecimal estimatedCost;
}