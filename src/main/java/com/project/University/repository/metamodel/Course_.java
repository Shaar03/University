package com.project.University.repository.metamodel;

import com.project.University.entity.Course;
import com.project.University.entity.Semester;
import com.project.University.entity.Student;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Course.class)
public class Course_ {

    public static volatile SingularAttribute<Course, Long> id;
    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, String> courseCode;
    public static volatile SingularAttribute<Course, Integer> creditHours;
    public static volatile SingularAttribute<Course, Integer> sectionNumber;
    public static volatile ListAttribute<Course, Student> students;
    public static volatile SingularAttribute<Course, Semester> semester;

    public static final String ID = "id";
    public static final String COURSE_NAME = "courseName";
    public static final String COURSE_CODE = "courseCode";
    public static final String CREDIT_HOURS = "creditHours";
    public static final String SECTION_NUMBER = "sectionNumber";
    public static final String STUDENTS = "students";
    public static final String SEMESTER = "semester";
}