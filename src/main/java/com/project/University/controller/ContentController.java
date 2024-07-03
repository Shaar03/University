package com.project.University.controller;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import com.project.University.service.CourseService;
import com.project.University.service.SemesterService;
import com.project.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/view")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class ContentController {

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    SemesterService semesterService;

    @GetMapping(path = "/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "/courses")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping(path = "/semesters")
    public List<Semester> getSemesters(){
        return semesterService.getSemesters();
    }
}