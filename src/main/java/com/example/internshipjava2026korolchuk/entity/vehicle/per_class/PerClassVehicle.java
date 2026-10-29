package com.example.internshipjava2026korolchuk.entity.vehicle.per_class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public abstract class PerClassVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String model;
    Long maxSpeed;
    Long fuelCapacity;
}
