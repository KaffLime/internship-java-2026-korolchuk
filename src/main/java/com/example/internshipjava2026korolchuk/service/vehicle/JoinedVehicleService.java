package com.example.internshipjava2026korolchuk.service.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedVehicle;
import com.example.internshipjava2026korolchuk.mapper.vehicle.JoinedVehicleMapper;
import com.example.internshipjava2026korolchuk.repository.vehicle.JoinedVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinedVehicleService {
    private final JoinedVehicleRepository joinedVehicleRepository;

    private final JoinedVehicleMapper joinedVehicleMapper;

    @GetMapping
    public List<JoinedVehicleDto> getAllVehicle() {
        ArrayList<JoinedVehicle> vehicleList = (ArrayList<JoinedVehicle>) joinedVehicleRepository.findAll();

        ArrayList<JoinedVehicleDto> vehicleDtoList = (ArrayList<JoinedVehicleDto>) vehicleList
                .stream().map(joinedVehicleMapper::toDto).toList();

        return vehicleDtoList;
    }

    @GetMapping("/{id}")
    public JoinedVehicleDto getVehicleById(@PathVariable Long id) {
        JoinedVehicle joinedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        return joinedVehicleMapper.toDto(joinedVehicle);
    }

    @GetMapping("/search")
    public List<JoinedVehicleDto> getVehicleByModel(@RequestParam String model) {
        ArrayList<JoinedVehicle> joinedVehicles = (ArrayList<JoinedVehicle>) joinedVehicleRepository.findByModel(model);

        return joinedVehicles.stream().map(joinedVehicleMapper::toDto).toList();
    }

    @PostMapping("/cars")
    public JoinedCarDto createCar(@RequestBody JoinedCarDto car) {
        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(joinedVehicleMapper.toEntity(car)));
    }

    @PostMapping("/planes")
    public JoinedPlaneDto createPlane(@RequestBody JoinedPlaneDto plane) {
        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(joinedVehicleMapper.toEntity(plane)));
    }

    @PostMapping("/trains")
    public JoinedTrainDto createTrain(@RequestBody JoinedTrainDto train) {
        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(joinedVehicleMapper.toEntity(train)));
    }

    @PatchMapping("/cars/{id}")
    public JoinedCarDto updateCar(@PathVariable Long id, @RequestBody JoinedCarDto car) {
        JoinedVehicle savedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        if (!(savedVehicle instanceof JoinedCar savedCar)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в машину");
        }

        joinedVehicleMapper.updateJoinedCarFromDto(car, savedCar);

        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(savedCar));
    }

    @PatchMapping("/planes/{id}")
    public JoinedPlaneDto updatePlane(@PathVariable Long id, @RequestBody JoinedPlaneDto plane) {
        JoinedVehicle savedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        if (!(savedVehicle instanceof JoinedPlane savedPlane)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в самолет");
        }

        joinedVehicleMapper.updateJoinedPlaneFromDto(plane, savedPlane);

        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(savedPlane));
    }

    @PatchMapping("/trains/{id}")
    public JoinedTrainDto updateTrain(@PathVariable Long id, @RequestBody JoinedTrainDto train) {
        JoinedVehicle savedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        if (!(savedVehicle instanceof JoinedTrain savedTrain)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в поезд");
        }

        joinedVehicleMapper.updateJoinedTrainFromDto(train, savedTrain);

        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(savedTrain));
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        joinedVehicleRepository.deleteById(id);
    }
}
