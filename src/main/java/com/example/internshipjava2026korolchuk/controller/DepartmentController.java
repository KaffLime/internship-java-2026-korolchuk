package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.DepartmentCreationDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentResponseDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentUpdatingDto;
import com.example.internshipjava2026korolchuk.service.DepartmentService;
import com.example.internshipjava2026korolchuk.specification.DepartmentFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public Page<DepartmentResponseDto> getAllDepartments(@Valid DepartmentFilter filter, Pageable pageable) {
        return departmentService.getAllDepartments(filter, pageable);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDto getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDto createDepartment(@Valid @RequestBody DepartmentCreationDto dto) {
        return departmentService.createDepartment(dto);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDto updateDepartment(@PathVariable Long id,
                                                  @Valid @RequestBody DepartmentUpdatingDto dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
