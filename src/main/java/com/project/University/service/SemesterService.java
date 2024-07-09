package com.project.University.service;

import com.project.University.entity.Semester;
import com.project.University.repository.SemesterRepository;
import com.project.University.repository.projection.SemesterIP;
import com.project.University.repository.specification.SemesterSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SemesterService {

    @Autowired
    SemesterRepository semesterRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public String registerSemester(Semester semester) {
        if(semesterRepository.findBySemesterName(semester.getSemesterName()).isPresent())
            return String.format("Semester %s already exists", semester.getSemesterName());

        semesterRepository.save(semester);
        return "Semester Successfully saved";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Page<SemesterIP> getSemesters(int pageNo, int pageSize){
        return semesterRepository.findAllProjectedBy(PageRequest.of(pageNo, pageSize));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<Semester> getSemesters(LocalDate providedDate, int pageNo, int pageSize) {
        Specification<Semester> spec = Specification.where(null);

        if(providedDate != null)
            spec = spec.and(SemesterSpecs.hasDate(providedDate));

        return semesterRepository.findAll(spec, PageRequest.of(pageNo, pageSize));
    }

    public long countSemesters() {
        return semesterRepository.count();
    }
}
