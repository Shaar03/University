package com.project.University.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    private String email;

    private int age;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> courses;
    @ManyToMany
    @JoinTable(
            name = "student_semester",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id")
    )
    @JsonIgnore
    private List<Semester> semesters;
    @Transient
    private List<String> semestersEnrolledHistory;
    @Transient
    private List<String> coursesEnrolledHistory;

    public List<String> getSemestersEnrolledHistory() {
        if(semesters != null) {
            return semesters.stream()
                    .map(Semester::getSemesterName)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<String> getCoursesEnrolledHistory() {
        if(courses != null) {
            return courses.stream()
                    .map(Course::getCourseName)
                    .collect(Collectors.toList());
        }
        return null;
    }
}