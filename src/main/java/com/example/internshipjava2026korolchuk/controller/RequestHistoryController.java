package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.RequestHistoryDto;
import com.example.internshipjava2026korolchuk.service.RequestHistoryService;
import com.example.internshipjava2026korolchuk.specification.RequestHistoryFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class RequestHistoryController {
    private final RequestHistoryService requestHistoryService;

    @GetMapping
    public Page<RequestHistoryDto> getWholeHistory(@Valid RequestHistoryFilter filter, Pageable pageable) {
        return requestHistoryService.getWholeHistory(filter, pageable);
    }

    @GetMapping("/{id}")
    public RequestHistoryDto getHistoryRecordById(@PathVariable Long id) {
        return requestHistoryService.getHistoryRecordById(id);
    }

    @PatchMapping("/{id}")
    public RequestHistoryDto updateHistoryRecord(@PathVariable Long id, @RequestParam String message) {
        return requestHistoryService.updateHistoryRecord(id, message);
    }
}
