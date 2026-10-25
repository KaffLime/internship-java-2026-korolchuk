package com.example.internshipjava2026korolchuk.entity.vehicle.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Plane")
@Getter
@Setter
@NoArgsConstructor
public class SinglePlane extends SingleVehicle {
    String planeType;
    Long massClass;

    SinglePlane(Long id, String model, Long maxSpeed, Long fuelCapacity, String planeType, Long massClass) {
        super(id, model, maxSpeed, fuelCapacity);

        this.planeType = planeType;
        this.massClass = massClass;
    }
}
