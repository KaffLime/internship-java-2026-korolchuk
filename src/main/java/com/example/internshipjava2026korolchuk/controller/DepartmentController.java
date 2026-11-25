package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.DepartmentCreationDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentResponseDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentUpdatingDto;
import com.example.internshipjava2026korolchuk.service.DepartmentService;
import com.example.internshipjava2026korolchuk.specification.DepartmentFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Departments")
@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @Tag(name = "Get")
    @Operation(summary = "Get all departments")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<DepartmentResponseDto> getAllDepartments(@Valid DepartmentFilter filter, Pageable pageable) {
        return departmentService.getAllDepartments(filter, pageable);
    }

    @Tag(name = "Get")
    @Operation(summary = "Get specific department by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentResponseDto getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @Tag(name = "Create")
    @Operation(summary = "Create department")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDto createDepartment(@Valid @RequestBody DepartmentCreationDto dto) {
        return departmentService.createDepartment(dto);
    }

    @Tag(name = "Update")
    @Operation(summary = "Update specific department entirely by id")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentResponseDto updateDepartment(@PathVariable Long id,
                                                  @Valid @RequestBody DepartmentUpdatingDto dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @Tag(name = "Delete")
    @Operation(summary = "Delete specific department by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
