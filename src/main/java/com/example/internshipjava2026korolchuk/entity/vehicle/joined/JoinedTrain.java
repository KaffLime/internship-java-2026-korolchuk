package com.example.internshipjava2026korolchuk.entity.vehicle.joined;

import jakarta.persistence.Entity;

@Entity
public class JoinedTrain extends JoinedVehicle {
    Long carriagesCount;
    String trainType;
}
