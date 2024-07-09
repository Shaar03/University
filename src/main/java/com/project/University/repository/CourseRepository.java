package com.project.University.repository;

import com.project.University.entity.Course;
import com.project.University.repository.projection.CourseIP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    Page<CourseIP> findAllProjectedBy(Pageable pageable);
    Optional<Course> findBySectionNumber(int sectionNumber);
}
