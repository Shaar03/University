package com.project.University.controller;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import com.project.University.repository.projection.StudentIP;
import com.project.University.service.CourseService;
import com.project.University.service.SemesterService;
import com.project.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "api")
public class ContentController {

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    SemesterService semesterService;
    @GetMapping(path = "/user/view/students")
    public PagedModel<StudentIP> getStudents(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = studentService.countStudents();

        int[] page = validatePage(pageNo, pageSize, totalRecords);

        return new PagedModel<>(studentService.getStudents(page[0], page[1]));
    }

    @GetMapping(path = "/admin/view/students")
    public PagedModel<Student> getStudents(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String courseName,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = studentService.countStudents();

        int[] page = validatePage(pageNo, pageSize, totalRecords);

        return new PagedModel<>(studentService.getStudents(age, studentName, courseName, page[0], page[1]));
    }

    @GetMapping(path = "/admin/view/courses")
    public PagedModel<Course> getCourses(
            @RequestParam(required = false) Integer creditHours,
            @RequestParam(required = false) Integer noOfStudents,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = courseService.countCourses();

        int[] page = validatePage(pageNo, pageSize, totalRecords);

        return new PagedModel<>(courseService.getCourses(creditHours, noOfStudents, page[0], page[1]));
    }

    @GetMapping(path = "/admin/view/semesters")
    public PagedModel<Semester> getSemesters(
            @RequestParam(required = false) LocalDate providedDate,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        long totalRecords = semesterService.countSemesters();

        int[] page = validatePage(pageNo, pageSize, totalRecords);

        return new PagedModel<>(semesterService.getSemesters(providedDate, page[0], page[1]));
    }

    private int[] validatePage(int pageNo, int pageSize, long totalRecords){
        int[] page = new int[2];
        page[0] = pageNo < 0 || pageNo > totalRecords / pageSize? 0: pageNo;
        page[1] = pageSize < 0 || pageSize > 5? 5: pageSize;

        return page;
    }
}