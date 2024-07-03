package com.project.University.controller;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import com.project.University.service.CourseService;
import com.project.University.service.SemesterService;
import com.project.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public org.springframework.data.web.PagedModel<Student> getStudents(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = studentService.countStudents();

        pageSize = pageSize < 0 || pageSize > 5? 5: pageSize;
        System.out.print(totalRecords);
        pageNo = pageNo < 0 || pageNo > totalRecords / pageSize? 0: pageNo;

        return new PagedModel<>(studentService.getStudents(pageNo, pageSize));
    }


    @GetMapping(path = "/courses")
    public org.springframework.data.web.PagedModel<Course> getCourses(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = courseService.countCourses();

        pageSize = pageSize < 0 || pageSize > 5? 5: pageSize;
        pageNo = pageNo < 0 || pageNo > totalRecords / pageSize? 0: pageNo;

        return new PagedModel<>(courseService.getCourses(pageNo, pageSize));
    }

    @GetMapping(path = "/semesters")
    public PagedModel<Semester> getSemesters(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = semesterService.countSemesters();

        pageSize = pageSize < 0 || pageSize > 5? 5: pageSize;
        pageNo = pageNo < 0 || pageNo > totalRecords / pageSize? 0: pageNo;

        return new PagedModel<>(semesterService.getSemesters(pageNo, pageSize));
    }
}