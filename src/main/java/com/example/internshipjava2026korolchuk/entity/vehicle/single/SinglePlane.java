package com.example.internshipjava2026korolchuk.entity.vehicle.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Plane")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinglePlane extends SingleVehicle {
    String planeType;
    Long massClass;
}
