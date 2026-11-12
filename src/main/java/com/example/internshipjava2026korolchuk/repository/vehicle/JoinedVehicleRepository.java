package com.example.internshipjava2026korolchuk.repository.vehicle;

import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinedVehicleRepository extends JpaRepository<JoinedVehicle, Long> {
    List<JoinedVehicle> findByModel(String model);

    @Query("SELECT c FROM JoinedCar c")
    List<JoinedCar> findAllCars();

    @Query("SELECT p FROM JoinedPlane p")
    List<JoinedPlane> findAllPlanes();

    @Query("SELECT t FROM JoinedTrain t")
    List<JoinedTrain> findAllTrains();
}
