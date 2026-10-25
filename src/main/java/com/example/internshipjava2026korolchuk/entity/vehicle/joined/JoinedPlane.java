package com.example.internshipjava2026korolchuk.entity.vehicle.joined;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JoinedPlane extends JoinedVehicle {
    String planeType;
    Long massClass;

    JoinedPlane(Long id, String model, Long maxSpeed, Long fuelCapacity, String planeType, Long massClass) {
        super(id, model, maxSpeed, fuelCapacity);

        this.planeType = planeType;
        this.massClass = massClass;
    }
}
