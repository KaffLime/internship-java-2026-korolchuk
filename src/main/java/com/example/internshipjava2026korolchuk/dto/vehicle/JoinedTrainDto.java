package com.example.internshipjava2026korolchuk.dto.vehicle;

public record JoinedTrainDto(
        Long id,
        String model,
        Long maxSpeed,
        Long fuelCapacity,
        Long carriagesCount,
        String trainType
) implements JoinedVehicleDto {
}
