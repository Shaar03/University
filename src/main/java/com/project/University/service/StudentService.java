package com.project.University.service;

import com.project.University.entity.Student;
import com.project.University.repository.StudentRepository;
import com.project.University.repository.projection.StudentBasic;
import com.project.University.repository.specification.StudentSpecs;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EntityManager entityManager;

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
    public Page<Student> getStudents(Integer age, String studentName, String courseName, Boolean isDeleted, int pageNo, int pageSize){
        Specification<Student> spec = Specification.where(null);

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedStudentFilter");
        filter.setParameter("isDeleted", isDeleted);

        if(age != null && age > 0)
            spec = spec.and(StudentSpecs.hasAgeGreaterThan(age));
        if(studentName != null && !studentName.isEmpty())
            spec = spec.and(StudentSpecs.hasName(studentName));
        if(courseName != null && !courseName.isEmpty())
            spec = spec.and(StudentSpecs.hasCourseName(courseName));

        Page<Student> students = studentRepository.findAll(spec, PageRequest.of(pageNo, pageSize));
        session.disableFilter("deletedStudentFilter");

        return students;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudent(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent() && !optionalStudent.get().isDeleted()) {
            studentRepository.deleteById(id);
            return "Deleted";
        }
        return "Student doesn't exist";
    }

    public long countStudents() {
        return studentRepository.count();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}