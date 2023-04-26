package com.example.restful.facultative.service;

import com.example.restful.facultative.model.Course;
import com.example.restful.facultative.model.Lecturer;
import com.example.restful.facultative.model.Student;

import java.util.List;

public interface EducationService {
    void createLecturer(Lecturer lecturer);

    void createStudent(Student student);

    void createCourse(Course course);

    List<Lecturer> readAllLecturers();

    Lecturer readLecturer(int lecturerId);

    List<Student> readAllStudents();

    Student readStudent(int studentId);

    List<Course> readAllCourses();

    Course readCourse(int courseId);

    int readEstimate(int courseId, int studentId);


    List<Student> readStudentsInCourse(int courseId);

    boolean updateLecturer(Lecturer lecturer, int lecturerId);

    boolean updateStudent(Student student, int studentId);

    boolean updateCourse(Course course, int courseId);

    boolean deleteStudent(int studentId);

    boolean deleteLecturer(int lecturerId);

    boolean deleteCourse(int courseId);
}
