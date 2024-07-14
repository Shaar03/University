package com.project.University.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE student SET is_deleted = true WHERE id=?")
@FilterDef(name = "deletedStudentFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedStudentFilter", condition = "is_deleted = :isDeleted")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private String email;

    private int age;

    private boolean isDeleted;

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