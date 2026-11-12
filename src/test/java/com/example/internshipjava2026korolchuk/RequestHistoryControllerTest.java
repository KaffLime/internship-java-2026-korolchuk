package com.example.internshipjava2026korolchuk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RequestHistoryControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/history")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value(-1))
                .andExpect(jsonPath("$.content[*].oldStatus").value(hasItem("DRAFT")));
    }

    @Test
    void testGetOne() throws Exception {
        mockMvc.perform(get("/api/v1/history/-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-1))
                .andExpect(jsonPath("$.oldStatus").value("DRAFT"));
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(patch("/api/v1/history/-1")
                        .param("message", "Test message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Test message"));
    }
}
