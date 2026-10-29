package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestResponseDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.service.TravelRequestService;
import com.example.internshipjava2026korolchuk.specification.TravelRequestFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/v1/travel-requests")
@RequiredArgsConstructor
public class TravelRequestController {

    private final TravelRequestService travelRequestService;

    @GetMapping
    public Page<TravelRequestResponseDto> getAllTravelRequests(@Valid TravelRequestFilter filter, Pageable pageable) {
        return travelRequestService.getAllTravelRequests(filter, pageable);
    }

    @GetMapping("/{id}")
    public TravelRequestResponseDto getTravelRequestById(@PathVariable Long id) {
        return travelRequestService.getTravelRequestById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelRequestResponseDto createTravelRequest(@Valid @RequestBody TravelRequestCreationDto dto) {
        return travelRequestService.createTravelRequest(dto);
    }

    @PutMapping("/{id}")
    public TravelRequestResponseDto updateTravelRequest(@PathVariable Long id,
                                                        @Valid @RequestBody TravelRequestUpdatingDto dto) {
        return travelRequestService.updateTravelRequest(id, dto);
    }

    @PatchMapping("/{id}/change-status")
    public TravelRequestResponseDto updateTravelRequestStatus(@PathVariable Long id,
                                                              @RequestParam TravelRequestStatus status) {
        return travelRequestService.updateTravelRequestStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTravelRequest(@PathVariable Long id) {
        travelRequestService.deleteTravelRequest(id);
    }
}
