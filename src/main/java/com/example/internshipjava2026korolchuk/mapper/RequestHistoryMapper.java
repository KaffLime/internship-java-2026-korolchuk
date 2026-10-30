package com.example.internshipjava2026korolchuk.mapper;

import com.example.internshipjava2026korolchuk.dto.RequestHistoryResponseDto;
import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestHistoryMapper {

    @Mapping(source = "travelRequest.id", target = "travelRequestId")
    RequestHistoryResponseDto toDto(RequestHistory travelRequest);
}
