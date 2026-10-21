package com.example.internshipjava2026korolchuk.service.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassVehicle;
import com.example.internshipjava2026korolchuk.mapper.vehicle.PerClassVehicleMapper;
import com.example.internshipjava2026korolchuk.repository.vehicle.PerClassVehicleRepository;
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
public class PerClassVehicleService {
    private final PerClassVehicleRepository perClassVehicleRepository;

    private final PerClassVehicleMapper perClassVehicleMapper;

    @GetMapping
    public List<PerClassVehicleDto> getAllVehicle() {
        ArrayList<PerClassVehicle> vehicleList = (ArrayList<PerClassVehicle>) perClassVehicleRepository.findAll();

        ArrayList<PerClassVehicleDto> vehicleDtoList = (ArrayList<PerClassVehicleDto>) vehicleList
                .stream().map(perClassVehicleMapper::toDto).toList();

        return vehicleDtoList;
    }

    @GetMapping("/{id}")
    public PerClassVehicleDto getVehicleById(@PathVariable Long id) {
        PerClassVehicle perClassVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        return perClassVehicleMapper.toDto(perClassVehicle);
    }

    @GetMapping("/search")
    public List<PerClassVehicleDto> getVehicleByModel(@RequestParam String model) {
        ArrayList<PerClassVehicle> perClassVehicles = (ArrayList<PerClassVehicle>) perClassVehicleRepository.findByModel(model);

        return perClassVehicles.stream().map(perClassVehicleMapper::toDto).toList();
    }

    @PostMapping("/cars")
    public PerClassCarDto createCar(@RequestBody PerClassCarDto car) {
        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(perClassVehicleMapper.toEntity(car)));
    }

    @PostMapping("/planes")
    public PerClassPlaneDto createPlane(@RequestBody PerClassPlaneDto plane) {
        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(perClassVehicleMapper.toEntity(plane)));
    }

    @PostMapping("/trains")
    public PerClassTrainDto createTrain(@RequestBody PerClassTrainDto train) {
        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(perClassVehicleMapper.toEntity(train)));
    }

    @PatchMapping("/cars/{id}")
    public PerClassCarDto updateCar(@PathVariable Long id, @RequestBody PerClassCarDto car) {
        PerClassVehicle savedVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        if (!(savedVehicle instanceof PerClassCar savedCar)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в машину");
        }

        perClassVehicleMapper.updatePerClassCarFromDto(car, savedCar);

        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(savedCar));
    }

    @PatchMapping("/planes/{id}")
    public PerClassPlaneDto updatePlane(@PathVariable Long id, @RequestBody PerClassPlaneDto plane) {
        PerClassVehicle savedVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        if (!(savedVehicle instanceof PerClassPlane savedPlane)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в самолет");
        }

        perClassVehicleMapper.updatePerClassPlaneFromDto(plane, savedPlane);

        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(savedPlane));
    }

    @PatchMapping("/trains/{id}")
    public PerClassTrainDto updateTrain(@PathVariable Long id, @RequestBody PerClassTrainDto train) {
        PerClassVehicle savedVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        if (!(savedVehicle instanceof PerClassTrain savedTrain)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в поезд");
        }

        perClassVehicleMapper.updatePerClassTrainFromDto(train, savedTrain);

        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(savedTrain));
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        perClassVehicleRepository.deleteById(id);
    }
}
