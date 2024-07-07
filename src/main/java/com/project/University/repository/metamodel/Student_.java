package com.project.University.repository.metamodel;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public class Student_ {

    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, String> studentName;
    public static volatile SingularAttribute<Student, String> email;
    public static volatile SingularAttribute<Student, Integer> age;
    public static volatile ListAttribute<Student, Course> courses;
    public static volatile ListAttribute<Student, Semester> semesters;

    public static final String ID = "id";
    public static final String STUDENT_NAME = "studentName";
    public static final String EMAIL = "email";
    public static final String AGE = "age";
    public static final String COURSES = "courses";
    public static final String SEMESTERS = "semesters";
}
