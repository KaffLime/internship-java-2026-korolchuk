package com.example.internshipjava2026korolchuk.entity.vehicle.per_class;

import jakarta.persistence.Entity;

@Entity
public class PerClassPlane extends PerClassVehicle {
    String planeType;
    Long massClass;
}
