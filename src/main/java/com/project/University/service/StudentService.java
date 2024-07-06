package com.project.University.service;

import com.project.University.entity.Student;
import com.project.University.repository.StudentRepository;
import com.project.University.repository.specification.StudentSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Page<Student> getStudents(Optional<Integer> age, int pageNo, int pageSize){
        if(age.isPresent())
            return studentRepository.findAll(StudentSpecs.ageGreaterThan(age), PageRequest.of(pageNo, pageSize));
        else
            return studentRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    public long countStudents() {
        return studentRepository.count();
    }
}