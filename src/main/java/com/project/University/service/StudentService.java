package com.project.University.service;

import com.project.University.entity.Student;
import com.project.University.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Student> getStudents(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return studentRepository.findAll(pageable);
    }

    public long countStudents() {
        return studentRepository.count();
    }
}
