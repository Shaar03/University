package com.project.University.repository;

import com.project.University.entity.Semester;
import com.project.University.repository.projection.SemesterBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SemesterRepository extends JpaRepository<Semester, Long>, JpaSpecificationExecutor<Semester> {
    <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> type);
    Optional<Semester> findBySemesterName(String semesterName);
}
