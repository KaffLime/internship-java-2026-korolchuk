package com.example.internshipjava2026korolchuk.dto.vehicle;

public record SingleTrainDto(
        Long id,
        String model,
        Long maxSpeed,
        Long fuelCapacity,
        Long carriagesCount,
        String trainType
) implements SingleVehicleDto {
}
