package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Department_;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecs {
    public static Specification<Department> containsName(String name) {
        return ((root, query, cb) -> {
            if (name == null) {
                return null;
            }

            String pattern = "%" + name.toLowerCase() + "%";
            return cb.like(cb.lower(root.get(Department_.NAME)), cb.lower(cb.literal(pattern)));
        });
    }

    public static Specification<Department> containsCode(String code) {
        return ((root, query, cb) -> {
            if (code == null) {
                return null;
            }

            String pattern = "%" + code + "%";
            return cb.like(cb.lower(root.get(Department_.CODE)), cb.lower(cb.literal(pattern)));
        });
    }
}
