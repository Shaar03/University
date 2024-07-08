package com.project.University.repository.specification;

import com.project.University.entity.Course;
import com.project.University.entity.Student;
import com.project.University.repository.metamodel.Course_;
import com.project.University.repository.metamodel.Student_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;


public class StudentSpecs {
    
    public static Specification<Student> hasCourseName(String courseName){
        return ((root, query, cb) -> {
            Join<Student, Course>  courseJoin = root.join(Student_.COURSES, JoinType.INNER);
           return cb.equal(courseJoin.get(Course_.COURSE_NAME), courseName);
        });
    }

    public static Specification<Student> hasAgeGreaterThan(Integer age){
        return ((root, query, cb) -> {
            return cb.greaterThan(root.get(Student_.AGE), age);
        });
    }

    public static Specification<Student> hasName(String studentName){
        return ((root, query, cb) -> {
            return cb.like(root.get(Student_.STUDENT_NAME), "%" + studentName +"%");
        });
    }
}