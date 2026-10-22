package com.example.internshipjava2026korolchuk.entity.vehicle.joined;

import jakarta.persistence.Entity;

@Entity
public class JoinedCar extends JoinedVehicle {
    String licensePlate;
    String vin;
    Boolean used;
}
