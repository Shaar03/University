package com.project.University.service;

import com.project.University.entity.Course;
import com.project.University.repository.CourseRepository;
import com.project.University.repository.specification.CourseSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public String registerCourse(Course course) {
        if(courseRepository.findBySectionNumber(course.getSectionNumber()).isPresent()){
            return String.format("Course with Section Number %d already exists", course.getSectionNumber());
        }
        courseRepository.save(course);
        return "Course Registered Successfully";
    }

    public Page<Course> getCourses(Integer creditHours, Integer noOfStudents, int pageNo, int pageSize) {
        Specification<Course> spec = Specification.where(null);

        if(creditHours != null)
            spec = spec.and(CourseSpecs.creditHoursGreaterThan(creditHours));
        if(noOfStudents != null)
            spec = spec.and(CourseSpecs.numberOfStudentsGreaterThan(noOfStudents));

        return courseRepository.findAll(spec, PageRequest.of(pageNo, pageSize));
    }

    public long countCourses() {
        return courseRepository.count();
    }
}