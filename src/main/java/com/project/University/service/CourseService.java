package com.project.University.service;

import com.project.University.entity.Course;
import com.project.University.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}