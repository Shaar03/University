package com.project.University.service;

import com.project.University.entity.Student;
import com.project.University.repository.StudentRepository;
import com.project.University.repository.projection.StudentBasic;
import com.project.University.repository.specification.StudentSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public String registerStudent(Student student){

        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            return "Email is used";
        }
        studentRepository.save(student);
        return "Registered Successfully";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Page<StudentBasic> getStudents(int pageNo, int pageSize){
        return studentRepository.findAllProjectedBy(PageRequest.of(pageNo, pageSize), StudentBasic.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<Student> getStudents(Integer age, String studentName, String courseName, int pageNo, int pageSize){
        Specification<Student> spec = Specification.where(null);

        if(age != null && age > 0)
            spec = spec.and(StudentSpecs.hasAgeGreaterThan(age));
        if(studentName != null && !studentName.isEmpty())
            spec = spec.and(StudentSpecs.hasName(studentName));
        if(courseName != null && !courseName.isEmpty())
            spec = spec.and(StudentSpecs.hasCourseName(courseName));

        return studentRepository.findAll(spec, PageRequest.of(pageNo, pageSize));
    }

    public long countStudents() {
        return studentRepository.count();
    }
}