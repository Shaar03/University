package com.project.University.service;

import com.project.University.entity.Semester;
import com.project.University.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    SemesterRepository semesterRepository;

    public String registerSemester(Semester semester) {
        if(semesterRepository.findBySemesterName(semester.getSemesterName()).isPresent())
            return String.format("Semester %s already exists", semester.getSemesterName());

        semesterRepository.save(semester);
        return "Semester Successfully saved";
    }

    public Page<Semester> getSemesters(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return semesterRepository.findAll(pageable);
    }

    public long countSemesters() {
        return semesterRepository.count();
    }
}
