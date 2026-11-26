package com.example.internshipjava2026korolchuk.feign;

import com.example.internshipjava2026korolchuk.dto.NotificationRequestDto;
import com.example.internshipjava2026korolchuk.dto.NotificationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", url = "${notification-service.url}")
public interface NotificationServiceClient {

    @GetMapping("/api/v1/history/{id}")
    Page<NotificationResponseDto> getTravelRequestHistory(@PathVariable("id") Long id, Pageable pageable);

    @PostMapping("/api/v1/history")
    NotificationResponseDto notificateStatusChange(NotificationRequestDto dto);
}
