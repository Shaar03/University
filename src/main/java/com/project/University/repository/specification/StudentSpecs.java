package com.project.University.repository.specification;

import com.project.University.entity.Student;
import com.project.University.repository.metamodel.Student_;
import org.springframework.data.jpa.domain.Specification;


public class StudentSpecs {

    public static Specification<Student> hasAgeGreaterThan(Integer age){
        return ((root, query, cb) -> {
            return cb.greaterThan(root.get(Student_.AGE), age);
        });
    }

    public static Specification<Student> hasName(String name){
        return ((root, query, cb) -> {
            return cb.like(root.get(Student_.STUDENT_NAME), "%" + name +"%");
        });
    }
}