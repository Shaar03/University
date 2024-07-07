package com.project.University.service;

import com.project.University.entity.Student;
import com.project.University.repository.StudentRepository;
import com.project.University.repository.specification.StudentSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    public Page<Student> getStudents(Integer age, String name, int pageNo, int pageSize){
        Specification<Student> spec = Specification.where(null);

        if(age != null)
            spec = spec.and(StudentSpecs.ageGreaterThan(age));
        if(name != null)
            spec = spec.and(StudentSpecs.nameIncludes(name));

        return studentRepository.findAll(spec, PageRequest.of(pageNo, pageSize));
    }

    public long countStudents() {
        return studentRepository.count();
    }
}