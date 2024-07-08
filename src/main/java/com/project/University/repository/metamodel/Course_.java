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
    public static final String ID = "id";
    public static final String COURSE_NAME = "courseName";
    public static final String COURSE_CODE = "courseCode";
    public static final String CREDIT_HOURS = "creditHours";
    public static final String SECTION_NUMBER = "sectionNumber";
    public static final String STUDENTS = "students";
    public static final String SEMESTER = "semester";
}