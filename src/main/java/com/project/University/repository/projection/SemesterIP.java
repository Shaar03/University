package com.project.University.repository.projection;

import java.time.LocalDate;

public interface SemesterIP {
    String getSemesterName();
    LocalDate getStartingDate();
    LocalDate getEndingDate();
}
