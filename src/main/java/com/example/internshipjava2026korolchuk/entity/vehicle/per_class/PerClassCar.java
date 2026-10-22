package com.example.internshipjava2026korolchuk.entity.vehicle.per_class;

import jakarta.persistence.Entity;

@Entity
public class PerClassCar extends PerClassVehicle {
    String licensePlate;
    String vin;
    Boolean used;
}
