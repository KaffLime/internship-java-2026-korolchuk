package com.example.internshipjava2026korolchuk.repository.vehicle;

import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerClassVehicleRepository extends JpaRepository<PerClassVehicle, Long> {
    List<PerClassVehicle> findByModel(String model);

    @Query("SELECT c FROM PerClassCar c")
    List<PerClassCar> findAllCars();

    @Query("SELECT p FROM PerClassPlane p")
    List<PerClassPlane> findAllPlanes();

    @Query("SELECT t FROM PerClassTrain t")
    List<PerClassTrain> findAllTrains();
}
