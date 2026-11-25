package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dto.RequestHistoryResponseDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RequestHistoryControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    void testGetAll() throws Exception {
        String result = mockMvc.perform(get("/api/v1/history")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode page = jsonMapper.readTree(result);
        JsonNode content = page.path("content");
        List<RequestHistoryResponseDto> history = jsonMapper.convertValue(content, new TypeReference<>() {
        });

        assertNotNull(history);
        assertEquals(1, page.path("totalElements").asLong());
        assertTrue(history.stream().anyMatch(x -> x.id() == -1L));
        assertTrue(history.stream().anyMatch(x -> x.oldStatus() == TravelRequestStatus.DRAFT));
    }

    @Test
    void testGetOne() throws Exception {
        String result = mockMvc.perform(get("/api/v1/history/-1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        RequestHistoryResponseDto history = jsonMapper.readValue(result, RequestHistoryResponseDto.class);
        assertNotNull(history);
        assertEquals(-1L, history.id());
        assertSame(TravelRequestStatus.DRAFT, history.oldStatus());
    }

    @Test
    void testUpdate() throws Exception {
        final String message = "Test message";

        String result = mockMvc.perform(patch("/api/v1/history/-1")
                        .param("message", message))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        RequestHistoryResponseDto history = jsonMapper.readValue(result, RequestHistoryResponseDto.class);
        assertNotNull(history);
        assertEquals(message, history.comment());
    }
}
