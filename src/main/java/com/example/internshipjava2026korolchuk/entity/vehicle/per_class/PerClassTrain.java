package com.example.internshipjava2026korolchuk.entity.vehicle.per_class;

import jakarta.persistence.Entity;

@Entity
public class PerClassTrain extends PerClassVehicle {
    Long carriagesCount;
    String trainType;
}
