package com.example.internshipjava2026korolchuk.entity.vehicle.joined;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JoinedCar extends JoinedVehicle {
    String licensePlate;
    String vin;
    Boolean used;

    JoinedCar(Long id, String model, Long maxSpeed, Long fuelCapacity, String licensePlate, String vin, Boolean used) {
        super(id, model, maxSpeed, fuelCapacity);

        this.licensePlate = licensePlate;
        this.vin = vin;
        this.used = used;
    }
}
