package com.project.University.service;

import com.project.University.entity.Course;
import com.project.University.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Course> getCourses(int pageNo, int pageSize) {
        return courseRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    public long countCourses() {
        return courseRepository.count();
    }
}