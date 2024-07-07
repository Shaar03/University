package com.project.University.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String courseCode;
    private int creditHours;
    private int sectionNumber;
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "semester_id")
    private Semester semester;
    @Transient
    private String semesterName;

    public String getSemesterName(){
        if (semester != null) {
            return semester.getSemesterName();
        }
        return null;
    }
}