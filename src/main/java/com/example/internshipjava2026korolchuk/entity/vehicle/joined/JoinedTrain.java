package com.example.internshipjava2026korolchuk.entity.vehicle.joined;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JoinedTrain extends JoinedVehicle {
    Long carriagesCount;
    String trainType;

    JoinedTrain(Long id, String model, Long maxSpeed, Long fuelCapacity, Long carriagesCount, String trainType) {
        super(id, model, maxSpeed, fuelCapacity);

        this.carriagesCount = carriagesCount;
        this.trainType = trainType;
    }
}
