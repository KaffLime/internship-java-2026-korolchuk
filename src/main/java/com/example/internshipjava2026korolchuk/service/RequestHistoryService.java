package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.RequestHistoryResponseDto;
import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.mapper.RequestHistoryMapper;
import com.example.internshipjava2026korolchuk.repository.RequestHistoryRepository;
import com.example.internshipjava2026korolchuk.specification.RequestHistoryFilter;
import com.example.starter.logging.Logginable;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestHistoryService {

    private final RequestHistoryRepository requestHistoryRepository;
    private final RequestHistoryMapper requestHistoryMapper;

    @Transactional(readOnly = true)
    @Logginable
    public Page<RequestHistoryResponseDto> getWholeHistory(RequestHistoryFilter filter, Pageable pageable) {
        Specification<RequestHistory> spec = RequestHistoryFilter.byFilter(filter);
        Page<RequestHistory> page = requestHistoryRepository.findAll(spec, pageable);

        return page.map(requestHistoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Logginable
    public RequestHistoryResponseDto getHistoryRecordById(Long id) {
        return requestHistoryMapper.toDto(requestHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись истории с id '" + id + "' не найдена")));
    }

    @Transactional
    @Logginable
    public void createHistoryRecord(TravelRequest travelRequest, TravelRequestStatus oldStatus) {
        requestHistoryRepository.save(new RequestHistory(
                null, travelRequest, oldStatus,
                travelRequest.getStatus(), null, "Запись успешно обновлена")
        );
    }

    @Transactional
    @Logginable
    public RequestHistoryResponseDto updateHistoryRecord(Long id, String comment) {
        RequestHistory requestHistory = requestHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись истории с id '" + id + "' не найдена"));

        if (comment != null) {
            requestHistory.setComment(comment);
        }

        return requestHistoryMapper.toDto(requestHistoryRepository.save(requestHistory));
    }
}
