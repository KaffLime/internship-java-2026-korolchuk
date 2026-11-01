package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestResponseDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.service.TravelRequestService;
import com.example.internshipjava2026korolchuk.specification.TravelRequestFilter;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Travel requests")
@RestController
@RequestMapping("/api/v1/travel-requests")
@RequiredArgsConstructor
public class TravelRequestController {

    private final TravelRequestService travelRequestService;

    @Tag(name = "Get")
    @Operation(summary = "Get all travel requests with brief departments and employees info")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TravelRequestResponseDto> getAllTravelRequests(@Valid TravelRequestFilter filter, Pageable pageable) {
        return travelRequestService.getAllTravelRequests(filter, pageable);
    }

    @Tag(name = "Get")
    @Operation(summary = "Get specific travel request by id with brief department and employee info")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TravelRequestResponseDto getTravelRequestById(@PathVariable Long id) {
        return travelRequestService.getTravelRequestById(id);
    }

    @Tag(name = "Create")
    @Operation(summary = "Create travel request")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TravelRequestResponseDto createTravelRequest(@Valid @RequestBody TravelRequestCreationDto dto) {
        return travelRequestService.createTravelRequest(dto);
    }

    @Tag(name = "Update")
    @Operation(summary = "Update specific travel request entirely by id")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TravelRequestResponseDto updateTravelRequest(@PathVariable Long id,
                                                        @Valid @RequestBody TravelRequestUpdatingDto dto) {
        return travelRequestService.updateTravelRequest(id, dto);
    }

    @Tag(name = "Update")
    @Operation(summary = "Update specific travel request status by id")
    @PatchMapping(value = "/{id}/change-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public TravelRequestResponseDto updateTravelRequestStatus(@PathVariable Long id,
                                                              @RequestParam TravelRequestStatus status) {
        return travelRequestService.updateTravelRequestStatus(id, status);
    }

    @Tag(name = "Delete")
    @Operation(summary = "Delete specific travel request by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTravelRequest(@PathVariable Long id) {
        travelRequestService.deleteTravelRequest(id);
    }
}
