package com.project.University.repository.specification;

import com.project.University.entity.Student;
import com.project.University.repository.metamode.Student_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class StudentSpecs {

    public static Specification<Student> ageGreaterThan(Optional<Integer> age){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get(Student_.AGE), age.get());
        });
    }
}