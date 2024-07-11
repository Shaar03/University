package com.project.University.controller;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import com.project.University.service.CourseService;
import com.project.University.service.EmailSenderService;
import com.project.University.service.SemesterService;
import com.project.University.service.StudentService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/register")
public class RegistrationController {

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    SemesterService semesterService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    JobScheduler jobScheduler;

    @PostMapping(path = "/student")
    public List<String> registerStudent(@RequestBody Student student){
        String result = studentService.registerStudent(student);
        jobScheduler.enqueue(() -> emailSenderService.sendEmail(
                student.getEmail(),
                "Registration",
                result
        ));

        return List.of(result);
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