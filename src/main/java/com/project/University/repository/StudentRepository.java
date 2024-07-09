package com.project.University.repository;

import com.project.University.entity.Student;
import com.project.University.repository.projection.StudentIP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Page<StudentIP> findAllProjectedBy(Pageable pageable);

    Optional<Student> findByEmail(String email);
}
