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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerClassVehicleService {
    private final PerClassVehicleRepository perClassVehicleRepository;
    private final PerClassVehicleMapper perClassVehicleMapper;

    @Transactional(readOnly = true)
    public List<PerClassVehicleDto> getAllVehicle() {
        return perClassVehicleRepository.findAll().stream().map(perClassVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public PerClassVehicleDto getVehicleById(@PathVariable Long id) {
        PerClassVehicle perClassVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        return perClassVehicleMapper.toDto(perClassVehicle);
    }

    @Transactional(readOnly = true)
    public List<PerClassVehicleDto> getVehicleByModel(@RequestParam String model) {
        ArrayList<PerClassVehicle> perClassVehicles = (ArrayList<PerClassVehicle>) perClassVehicleRepository.findByModel(model);

        return perClassVehicles.stream().map(perClassVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<PerClassCarDto> getAllCars() {
        return perClassVehicleRepository.findAllCars().stream().map(perClassVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<PerClassPlaneDto> getAllPlanes() {
        return perClassVehicleRepository.findAllPlanes().stream().map(perClassVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<PerClassTrainDto> getAllTrains() {
        return perClassVehicleRepository.findAllTrains().stream().map(perClassVehicleMapper::toDto).toList();
    }

    @Transactional
    public PerClassCarDto createCar(@RequestBody PerClassCarDto car) {
        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(perClassVehicleMapper.toEntity(car)));
    }

    @Transactional
    public PerClassPlaneDto createPlane(@RequestBody PerClassPlaneDto plane) {
        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(perClassVehicleMapper.toEntity(plane)));
    }

    @Transactional
    public PerClassTrainDto createTrain(@RequestBody PerClassTrainDto train) {
        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(perClassVehicleMapper.toEntity(train)));
    }

    @Transactional
    public PerClassCarDto updateCar(@PathVariable Long id, @RequestBody PerClassCarDto car) {
        PerClassVehicle savedVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        if (!(savedVehicle instanceof PerClassCar savedCar)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в машину");
        }

        perClassVehicleMapper.updatePerClassCarFromDto(car, savedCar);

        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(savedCar));
    }

    @Transactional
    public PerClassPlaneDto updatePlane(@PathVariable Long id, @RequestBody PerClassPlaneDto plane) {
        PerClassVehicle savedVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        if (!(savedVehicle instanceof PerClassPlane savedPlane)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в самолет");
        }

        perClassVehicleMapper.updatePerClassPlaneFromDto(plane, savedPlane);

        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(savedPlane));
    }

    @Transactional
    public PerClassTrainDto updateTrain(@PathVariable Long id, @RequestBody PerClassTrainDto train) {
        PerClassVehicle savedVehicle = perClassVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("PerClass транспорта с таким id не существует")));

        if (!(savedVehicle instanceof PerClassTrain savedTrain)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в поезд");
        }

        perClassVehicleMapper.updatePerClassTrainFromDto(train, savedTrain);

        return perClassVehicleMapper.toDto(perClassVehicleRepository.save(savedTrain));
    }

    @Transactional
    public void deleteVehicle(@PathVariable Long id) {
        perClassVehicleRepository.deleteById(id);
    }
}
