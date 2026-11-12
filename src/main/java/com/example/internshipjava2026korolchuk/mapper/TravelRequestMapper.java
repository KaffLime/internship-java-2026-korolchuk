package com.example.internshipjava2026korolchuk.mapper;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestResponseDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TravelRequestMapper {

    TravelRequestResponseDto toDto(TravelRequest travelRequest);

    @Mapping(source = "employeeId", target = "employee")
    @Mapping(source = "departmentId", target = "department")
    TravelRequest toEntity(TravelRequestCreationDto dto);

    void updateTravelRequestFromDto(TravelRequestUpdatingDto dto, @MappingTarget TravelRequest travelRequest);

    default Employee mapEmployee(Long id) {
        if (id == null) {
            return null;
        }

        Employee emp = new Employee();
        emp.setId(id);

        return emp;
    }

    default Department mapDepartment(Long id) {
        if (id == null) {
            return null;
        }

        Department dep = new Department();
        dep.setId(id);

        return dep;
    }
}
