package com.project.University.repository.specification;

import com.project.University.entity.Semester;
import com.project.University.repository.metamodel.Semester_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SemesterSpecs {


    public static Specification<Semester> hasDate(LocalDate providedDate){
        return ((root, query, cb) -> {
            return cb.between(
                    cb.literal(providedDate),
                    root.get(Semester_.STARTING_DATE),
                    root.get(Semester_.ENDING_DATE)
            );
        });
    }
}
