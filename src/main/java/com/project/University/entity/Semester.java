package com.project.University.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semesterName;
    private LocalDate startingDate;
    private LocalDate endingDate;
    @OneToMany(mappedBy = "semester")
    private List<Course> courses;
    @ManyToMany(mappedBy = "semesters")
    private List<Student> students;
}
