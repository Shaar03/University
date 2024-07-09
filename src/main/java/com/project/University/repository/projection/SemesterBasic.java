package com.project.University.repository.projection;

import java.time.LocalDate;

public interface SemesterBasic {
    String getSemesterName();
    LocalDate getStartingDate();
    LocalDate getEndingDate();
}
