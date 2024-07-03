package com.project.University.service;

import com.project.University.entity.Student;
import com.project.University.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String registerStudent(Student student){
        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            return "Email is used";
        }
        studentRepository.save(student);
        return "Registered Successfully";
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
