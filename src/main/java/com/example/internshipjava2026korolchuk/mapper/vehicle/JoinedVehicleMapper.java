package com.example.internshipjava2026korolchuk.mapper.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface JoinedVehicleMapper {

    @SubclassMapping(source = JoinedCar.class, target = JoinedCarDto.class)
    @SubclassMapping(source = JoinedPlane.class, target = JoinedPlaneDto.class)
    @SubclassMapping(source = JoinedTrain.class, target = JoinedTrainDto.class)
    JoinedVehicleDto toDto(JoinedVehicle joinedVehicle);

    @SubclassMapping(source = JoinedCarDto.class, target = JoinedCar.class)
    @SubclassMapping(source = JoinedPlaneDto.class, target = JoinedPlane.class)
    @SubclassMapping(source = JoinedTrainDto.class, target = JoinedTrain.class)
    JoinedVehicle toEntity(JoinedVehicleDto joinedVehicleDto);

    JoinedCarDto toDto(JoinedCar joinedCar);

    JoinedPlaneDto toDto(JoinedPlane joinedPlane);

    JoinedTrainDto toDto(JoinedTrain joinedTrain);

    JoinedCar toEntity(JoinedCarDto dto);

    JoinedPlane toEntity(JoinedPlaneDto dto);

    JoinedTrain toEntity(JoinedTrainDto dto);

    @Mapping(target = "id", ignore = true)
    void updateJoinedCarFromDto(JoinedCarDto dto, @MappingTarget JoinedCar car);

    @Mapping(target = "id", ignore = true)
    void updateJoinedPlaneFromDto(JoinedPlaneDto dto, @MappingTarget JoinedPlane plane);

    @Mapping(target = "id", ignore = true)
    void updateJoinedTrainFromDto(JoinedTrainDto dto, @MappingTarget JoinedTrain train);
}
