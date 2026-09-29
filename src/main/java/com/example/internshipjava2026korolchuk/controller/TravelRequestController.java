package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.service.TravelRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/travelRequests")
@RequiredArgsConstructor
public class TravelRequestController {
    private final TravelRequestService travelRequestService;

    @GetMapping
    public List<TravelRequestDto> getAllTravelRequests() {
        return travelRequestService.getAllTravelRequests();
    }

    @GetMapping("/{id}")
    public TravelRequestDto getTravelRequestById(@PathVariable Long id) {
        return travelRequestService.getTravelRequestById(id);
    }

    @PostMapping
    public TravelRequestDto createTravelRequest(@RequestBody TravelRequestDto travelRequestDTO) {
        return travelRequestService.createTravelRequest(travelRequestDTO);
    }

    @PatchMapping("/{id}")
    public TravelRequestDto updateTravelRequest(@PathVariable Long id, @RequestBody TravelRequestDto travelRequestDTO) {
        return travelRequestService.updateTravelRequest(id, travelRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTravelRequest(@PathVariable Long id) {
        travelRequestService.deleteTravelRequest(id);
    }
}
