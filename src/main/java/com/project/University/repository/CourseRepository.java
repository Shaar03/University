package com.project.University.repository;

import com.project.University.entity.Course;
import com.project.University.repository.projection.CourseBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> type);
    Optional<Course> findBySectionNumber(int sectionNumber);
}
