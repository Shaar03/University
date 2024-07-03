package com.project.University.controller;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import com.project.University.service.CourseService;
import com.project.University.service.SemesterService;
import com.project.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/register")
@PreAuthorize("hasRole('ADMIN')")
public class RegistrationController {

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    SemesterService semesterService;

    @PostMapping(path = "/student")
    public List<String> registerStudent(@RequestBody Student student){
        return List.of(studentService.registerStudent(student));
    }

    @PostMapping(path = "/course")
    public List<String> registerCourse(@RequestBody Course course){
        return List.of(courseService.registerCourse(course));
    }

    @PostMapping(path = "/semester")
    public List<String> registerSemester(@RequestBody Semester semester){
        return List.of(semesterService.registerSemester(semester));
    }
}