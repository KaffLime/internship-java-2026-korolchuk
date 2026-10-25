package com.example.internshipjava2026korolchuk.entity.vehicle.per_class;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PerClassPlane extends PerClassVehicle {
    String planeType;
    Long massClass;

    PerClassPlane(Long id, String model, Long maxSpeed, Long fuelCapacity, String planeType, Long massClass) {
        super(id, model, maxSpeed, fuelCapacity);

        this.planeType = planeType;
        this.massClass = massClass;
    }
}
