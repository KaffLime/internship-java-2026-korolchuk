package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.mapper.TravelRequestMapper;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import com.example.internshipjava2026korolchuk.specification.TravelRequestFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TravelRequestService {
    private final TravelRequestRepository travelRequestRepository;
    private final TravelRequestMapper travelRequestMapper;

    @Transactional(readOnly = true)
    public Page<TravelRequestDto> getAllTravelRequests(TravelRequestFilter filter, Pageable pageable) {
        Specification<TravelRequest> spec = TravelRequestFilter.byFilter(filter);
        Page<TravelRequest> page = travelRequestRepository.findAll(spec, pageable);

        return page.map(travelRequestMapper::toDto);
    }

    @Transactional(readOnly = true)
    public TravelRequestDto getTravelRequestById(Long id) {
        return travelRequestMapper.toDto(travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена")));
    }

    @Transactional
    public TravelRequestDto createTravelRequest(TravelRequestDto travelRequestDto) {
        return travelRequestMapper.toDto(travelRequestRepository.save(travelRequestMapper.toEntity(travelRequestDto)));
    }

    @Transactional
    public TravelRequestDto updateTravelRequest(Long id, TravelRequestDto travelRequestDto) {
        TravelRequest travelRequest = travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена"));

        travelRequestMapper.updateTravelRequestFromDto(travelRequestDto, travelRequest);

        return travelRequestMapper.toDto(travelRequestRepository.save(travelRequest));
    }

    @Transactional
    public void deleteTravelRequest(Long id) {
        travelRequestRepository.deleteById(id);
    }
}
