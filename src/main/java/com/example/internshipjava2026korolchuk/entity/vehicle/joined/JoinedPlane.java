package com.example.internshipjava2026korolchuk.entity.vehicle.joined;

import jakarta.persistence.Entity;

@Entity
public class JoinedPlane extends JoinedVehicle {
    String planeType;
    Long massClass;
}
