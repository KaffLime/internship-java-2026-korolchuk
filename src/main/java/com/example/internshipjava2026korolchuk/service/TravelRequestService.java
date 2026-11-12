package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestResponseDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.exception.CannotUpdateTravelRequestWithStatusException;
import com.example.internshipjava2026korolchuk.exception.NotValidDtoException;
import com.example.internshipjava2026korolchuk.mapper.TravelRequestMapper;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import com.example.internshipjava2026korolchuk.specification.TravelRequestFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
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
    private final RequestHistoryService requestHistoryService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Page<TravelRequestResponseDto> getAllTravelRequests(TravelRequestFilter filter, Pageable pageable) {
        Specification<TravelRequest> spec = TravelRequestFilter.byFilter(filter);
        Page<TravelRequest> page = travelRequestRepository.findAll(spec, pageable);

        return page.map(travelRequestMapper::toDto);
    }

    @Transactional(readOnly = true)
    public TravelRequestResponseDto getTravelRequestById(Long id) {
        return travelRequestMapper.toDto(travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена")));
    }

    @Transactional
    public TravelRequestResponseDto createTravelRequest(TravelRequestCreationDto dto) {
        if (dto.departmentId() == null || dto.employeeId() == null) {
            throw new NotValidDtoException("Заявка не может существовать без отдела или сотрудника");
        }

        TravelRequest tr = travelRequestRepository.saveAndFlush(travelRequestMapper.toEntity(dto));
        entityManager.refresh(tr);

        return travelRequestMapper.toDto(tr);
    }

    @Transactional
    public TravelRequestResponseDto updateTravelRequest(Long id, TravelRequestUpdatingDto dto) {
        TravelRequest travelRequest = travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена"));

        TravelRequestStatus status = travelRequest.getStatus();

        if (status == TravelRequestStatus.APPROVED || status == TravelRequestStatus.REJECTED) {
            throw new CannotUpdateTravelRequestWithStatusException(
                    "Нельзя обновлять заявку в статусе " + status);
        }

        travelRequestMapper.updateTravelRequestFromDto(dto, travelRequest);

        TravelRequest tr = travelRequestRepository.saveAndFlush(travelRequest);
        entityManager.refresh(tr);

        return travelRequestMapper.toDto(tr);
    }

    @Transactional
    public TravelRequestResponseDto updateTravelRequestStatus(Long id, TravelRequestStatus newStatus) {
        TravelRequest travelRequest = travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена"));

        if (travelRequest.getStatus() == newStatus) {
            throw new CannotUpdateTravelRequestWithStatusException("Заявка уже находится в указанном статусе");
        }

        TravelRequestStatus oldStatus = travelRequest.getStatus();
        travelRequest.setStatus(newStatus);

        TravelRequest savedTravelRequest = travelRequestRepository.save(travelRequest);
        requestHistoryService.createHistoryRecord(travelRequest, oldStatus);

        return travelRequestMapper.toDto(savedTravelRequest);
    }

    @Transactional
    public void deleteTravelRequest(Long id) {
        travelRequestRepository.deleteById(id);
    }
}
