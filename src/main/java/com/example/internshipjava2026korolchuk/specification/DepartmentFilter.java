package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.Department;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Builder
@Getter
@Setter
public class DepartmentFilter {
    String name;

    @Size(min = 3, max = 10, message = "Длина кода должна составлять от 3 до 10 символов")
    String code;

    public static Specification<Department> byFilter(DepartmentFilter filter) {
        return Specification.allOf(
                DepartmentSpecs.containsName(filter.getName()),
                DepartmentSpecs.containsCode(filter.getCode())
        );
    }
}
