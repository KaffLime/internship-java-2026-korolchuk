package com.example.internshipjava2026korolchuk.mapper;

import com.example.internshipjava2026korolchuk.dto.RequestHistoryDto;
import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestHistoryMapper {
    RequestHistoryDto toDto(RequestHistory travelRequest);

    @Mapping(target = "id", ignore = true)
    RequestHistory toEntity(RequestHistoryDto dto);

    @Mapping(target = "id", ignore = true)
    void updateRequestHistoryFromDto(RequestHistoryDto dto, @MappingTarget RequestHistory requestHistory);
}
