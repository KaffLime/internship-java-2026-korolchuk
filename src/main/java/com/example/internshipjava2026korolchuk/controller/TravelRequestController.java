package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.service.TravelRequestService;
import com.example.internshipjava2026korolchuk.specification.TravelRequestFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel-requests")
@RequiredArgsConstructor
public class TravelRequestController {
    private final TravelRequestService travelRequestService;

    @GetMapping
    public Page<TravelRequestDto> getAllTravelRequests(@Valid TravelRequestFilter filter, Pageable pageable) {
        return travelRequestService.getAllTravelRequests(filter, pageable);
    }

    @GetMapping("/{id}")
    public TravelRequestDto getTravelRequestById(@PathVariable Long id) {
        return travelRequestService.getTravelRequestById(id);
    }

    @PostMapping
    public TravelRequestDto createTravelRequest(@Valid @RequestBody TravelRequestDto travelRequestDTO) {
        return travelRequestService.createTravelRequest(travelRequestDTO);
    }

    @PatchMapping("/{id}")
    public TravelRequestDto updateTravelRequest(@PathVariable Long id,
                                                @Valid @RequestBody TravelRequestDto travelRequestDTO) {
        return travelRequestService.updateTravelRequest(id, travelRequestDTO);
    }

    @PatchMapping("/{id}/change-status")
    public TravelRequestDto updateTravelRequestStatus(@PathVariable Long id,
                                                      @RequestParam TravelRequestStatus status) {
        return travelRequestService.updateTravelRequestStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTravelRequest(@PathVariable Long id) {
        travelRequestService.deleteTravelRequest(id);
    }
}
