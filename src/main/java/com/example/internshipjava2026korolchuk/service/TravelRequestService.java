package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelRequestService {
    private final TravelRequestRepository travelRequestRepository;
    private final TravelRequestMapper travelRequestMapper;

    @Transactional(readOnly = true)
    public List<TravelRequestDto> getAllTravelRequests() {
        ArrayList<TravelRequest> travelRequests = (ArrayList<TravelRequest>) travelRequestRepository.findAll();
        ArrayList<TravelRequestDto> travelRequestDtos = new ArrayList<>();

        for (TravelRequest travelRequest : travelRequests) {
            travelRequestDtos.add(travelRequestMapper.toDto(travelRequest));
        }

        return travelRequestDtos;
    }

    @Transactional(readOnly = true)
    public TravelRequestDto getTravelRequestById(Long id) {
        return travelRequestMapper.toDto(travelRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Командировки с таким id не существует")));
    }

    @Transactional
    public TravelRequestDto createTravelRequest(TravelRequestDto travelRequestDto) {
        return travelRequestMapper.toDto(travelRequestRepository.save(travelRequestMapper.toEntity(travelRequestDto)));
    }

    @Transactional
    public TravelRequestDto updateTravelRequest(Long id, TravelRequestDto travelRequestDto) {
        TravelRequest travelRequest = travelRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Командировки с таким id не существует"));

        travelRequestMapper.updateTravelRequestFromDto(travelRequestDto, travelRequest);

        return travelRequestMapper.toDto(travelRequestRepository.save(travelRequest));
    }

    @Transactional
    public void deleteTravelRequest(Long id) {
        travelRequestRepository.deleteById(id);
    }
}
