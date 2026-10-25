package com.example.internshipjava2026korolchuk.entity.vehicle.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Car")
@Getter
@Setter
@NoArgsConstructor
public class SingleCar extends SingleVehicle {
    String licensePlate;
    String vin;
    Boolean used;

    SingleCar(Long id, String model, Long maxSpeed, Long fuelCapacity, String licensePlate, String vin, Boolean used) {
        super(id, model, maxSpeed, fuelCapacity);

        this.licensePlate = licensePlate;
        this.vin = vin;
        this.used = used;
    }
}
