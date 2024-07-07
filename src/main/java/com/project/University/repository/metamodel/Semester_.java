package com.project.University.repository.metamodel;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Semester.class)
public class Semester_ {

    public static volatile SingularAttribute<Semester, Long> id;
    public static volatile SingularAttribute<Semester, String> semesterName;
    public static volatile SingularAttribute<Semester, LocalDate> startingDate;
    public static volatile SingularAttribute<Semester, LocalDate> endingDate;
    public static volatile ListAttribute<Semester, Course> courses;
    public static volatile SingularAttribute<Semester, Student> students;

    public static final String ID = "id";
    public static final String SEMESTER_NAME = "semsterName";
    public static final String STARTING_DATE = "startingDate";
    public static final String ENDING_DATE = "endingDate";
    public static final String COURSES = "courses";
    public static final String STUDENTS = "students";
}