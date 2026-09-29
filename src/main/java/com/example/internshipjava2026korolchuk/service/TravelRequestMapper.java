package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TravelRequestMapper {
    TravelRequestDto toDto(TravelRequest travelRequest);

    TravelRequest toEntity(TravelRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void updateTravelRequestFromDto(TravelRequestDto dto, @MappingTarget TravelRequest travelRequest);
}
