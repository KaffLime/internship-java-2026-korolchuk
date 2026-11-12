package com.example.internshipjava2026korolchuk.mapper.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface PerClassVehicleMapper {

    @SubclassMapping(source = PerClassCar.class, target = PerClassCarDto.class)
    @SubclassMapping(source = PerClassPlane.class, target = PerClassPlaneDto.class)
    @SubclassMapping(source = PerClassTrain.class, target = PerClassTrainDto.class)
    PerClassVehicleDto toDto(PerClassVehicle perClassVehicle);

    @SubclassMapping(source = PerClassCarDto.class, target = PerClassCar.class)
    @SubclassMapping(source = PerClassPlaneDto.class, target = PerClassPlane.class)
    @SubclassMapping(source = PerClassTrainDto.class, target = PerClassTrain.class)
    PerClassVehicle toEntity(PerClassVehicleDto perClassVehicleDto);

    PerClassCarDto toDto(PerClassCar perClassCar);

    PerClassPlaneDto toDto(PerClassPlane perClassPlane);

    PerClassTrainDto toDto(PerClassTrain perClassTrain);

    PerClassCar toEntity(PerClassCarDto dto);

    PerClassPlane toEntity(PerClassPlaneDto dto);

    PerClassTrain toEntity(PerClassTrainDto dto);

    @Mapping(target = "id", ignore = true)
    void updatePerClassCarFromDto(PerClassCarDto dto, @MappingTarget PerClassCar car);

    @Mapping(target = "id", ignore = true)
    void updatePerClassPlaneFromDto(PerClassPlaneDto dto, @MappingTarget PerClassPlane plane);

    @Mapping(target = "id", ignore = true)
    void updatePerClassTrainFromDto(PerClassTrainDto dto, @MappingTarget PerClassTrain train);
}
