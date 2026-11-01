package com.example.internshipjava2026korolchuk;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "API for travel requests management",
                version = "1.0",
                description = "Project for senla internship in 2026 by Korolchuk"
        ),
        tags = {
                @Tag(name = "Departments"),
                @Tag(name = "Employees"),
                @Tag(name = "Travel requests"),
                @Tag(name = "Requests history"),
                @Tag(name = "Get"),
                @Tag(name = "Create"),
                @Tag(name = "Update"),
                @Tag(name = "Delete")
        }
)
@SpringBootApplication
public class InternshipJava2026KorolchukApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipJava2026KorolchukApplication.class, args);
    }
}