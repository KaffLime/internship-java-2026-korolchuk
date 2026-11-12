package com.example.internshipjava2026korolchuk.controller.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedVehicleDto;
import com.example.internshipjava2026korolchuk.service.vehicle.JoinedVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/joinedVehicles")
@RequiredArgsConstructor
public class JoinedVehicleController {
    private final JoinedVehicleService joinedVehicleService;

    @GetMapping
    public List<JoinedVehicleDto> getAllVehicle() {
        return joinedVehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public JoinedVehicleDto getVehicleById(@PathVariable Long id) {
        return joinedVehicleService.getVehicleById(id);
    }

    @GetMapping("/search")
    public List<JoinedVehicleDto> getVehicleByModel(@RequestParam String model) {
        return joinedVehicleService.getVehicleByModel(model);
    }

    @GetMapping("/cars")
    public List<JoinedCarDto> getAllCars() {
        return joinedVehicleService.getAllCars();
    }

    @GetMapping("/planes")
    public List<JoinedPlaneDto> getAllPlanes() {
        return joinedVehicleService.getAllPlanes();
    }

    @GetMapping("/trains")
    public List<JoinedTrainDto> getAllTrains() {
        return joinedVehicleService.getAllTrains();
    }

    @PostMapping("/cars")
    public JoinedCarDto createCar(@RequestBody JoinedCarDto car) {
        return joinedVehicleService.createCar(car);
    }

    @PostMapping("/planes")
    public JoinedPlaneDto createPlane(@RequestBody JoinedPlaneDto plane) {
        return joinedVehicleService.createPlane(plane);
    }

    @PostMapping("/trains")
    public JoinedTrainDto createTrain(@RequestBody JoinedTrainDto train) {
        return joinedVehicleService.createTrain(train);
    }

    @PatchMapping("/cars/{id}")
    public JoinedCarDto updateCar(@PathVariable Long id, @RequestBody JoinedCarDto car) {
        return joinedVehicleService.updateCar(id, car);
    }

    @PatchMapping("/planes/{id}")
    public JoinedPlaneDto updatePlane(@PathVariable Long id, @RequestBody JoinedPlaneDto plane) {
        return joinedVehicleService.updatePlane(id, plane);
    }

    @PatchMapping("/trains/{id}")
    public JoinedTrainDto updateTrain(@PathVariable Long id, @RequestBody JoinedTrainDto train) {
        return joinedVehicleService.updateTrain(id, train);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        joinedVehicleService.deleteVehicle(id);
    }
}
