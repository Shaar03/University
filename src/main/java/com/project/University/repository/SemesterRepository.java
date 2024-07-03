package com.project.University.repository;

import com.project.University.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Optional<Semester> findBySemesterName(String semesterName);
}
