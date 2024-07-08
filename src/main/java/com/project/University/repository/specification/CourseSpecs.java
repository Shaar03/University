package com.project.University.repository.specification;

import com.project.University.entity.Course;
import com.project.University.repository.metamodel.Course_;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecs {

    public static Specification<Course> hasCreditHoursGreaterThan(Integer creditHours){
        return ((root, query, cb) -> {
            return cb.greaterThan(root.get(Course_.CREDIT_HOURS), creditHours);
        });
    }

    public static Specification<Course> hasNoOfStudentsGreaterThan(Integer noOfStudents){
        return ((root, query, cb) -> {
            return cb.greaterThan(cb.size(root.get(Course_.STUDENTS)), noOfStudents);
        });
    }
}
