package com.example.restful.facultative.service;


import com.example.restful.facultative.model.Course;
import com.example.restful.facultative.model.Lecturer;
import com.example.restful.facultative.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EducationServiceImpl implements EducationService {

    //збереження загального списку клієнтів, ключ-id, значення - клієнт
      private static final Map<Integer, Course> CLIENT_REPOSITORY_MAP = new HashMap<>();
    private static final Map<Integer, Lecturer> LECTURER_REPOSITORY_MAP = new HashMap<>();
    private static final Map<Integer, Student> STUDENT_REPOSITORY_MAP = new HashMap<>();
    private static final Map<Integer, Course> COURSE_REPOSITORY_MAP = new HashMap<>();


    private static final AtomicInteger LECTURER_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger STUDENT_ID_HOLDER = new AtomicInteger();
    private static final AtomicInteger COURSE_ID_HOLDER = new AtomicInteger();

    @Override
    public void createLecturer(Lecturer lecturer) {
        final int lecturerId = LECTURER_ID_HOLDER.incrementAndGet();
        lecturer.setLecturerId(lecturerId);
        LECTURER_REPOSITORY_MAP.put(lecturerId, lecturer);
    }

    @Override
    public void createStudent(Student student) {
        final int studentId = STUDENT_ID_HOLDER.incrementAndGet();
        student.setStudentId(studentId);

        STUDENT_REPOSITORY_MAP.put(studentId, student);
    }

    @Override
    public void createCourse(Course course) {
        final int courseId = COURSE_ID_HOLDER.incrementAndGet();
        course.setCourseId(courseId);
        COURSE_REPOSITORY_MAP.put(courseId, course);
    }



    @Override
    public List<Lecturer> readAllLecturers() {
        return new ArrayList<>(LECTURER_REPOSITORY_MAP.values());
    }

    @Override
    public Lecturer readLecturer(int lecturerId) {
        return LECTURER_REPOSITORY_MAP.get(lecturerId);
    }

    @Override
    public List<Student> readAllStudents() {
        return new ArrayList<>(STUDENT_REPOSITORY_MAP.values());
    }

    @Override
    public Student readStudent(int studentId) {
        return STUDENT_REPOSITORY_MAP.get(studentId);
    }

    @Override
    public List<Course> readAllCourses() {
        return new ArrayList<>(COURSE_REPOSITORY_MAP.values());
    }

    @Override
    public Course readCourse(int courseId) {
        return COURSE_REPOSITORY_MAP.get(courseId);
    }

    @Override
    public int readEstimate(int courseId, int studentId) {
        int[][] studentEstimates = COURSE_REPOSITORY_MAP.get(courseId).getStudentEstimates();
        return studentEstimates[studentId-1][1];
    }

    @Override
    public List<Student> readStudentsInCourse(int courseId) {
        List studentList = new ArrayList();
        int[][] studentEstimates = COURSE_REPOSITORY_MAP.get(courseId).getStudentEstimates();
        for(int i=0;i<studentEstimates.length;i++){
            studentList.add(STUDENT_REPOSITORY_MAP.get(studentEstimates[i][0]));
        }

        return studentList;

    }




    @Override
    public boolean updateLecturer(Lecturer lecturer, int lecturerId) {
        if (LECTURER_REPOSITORY_MAP.containsKey(lecturerId)) {
            lecturer.setLecturerId(lecturerId);
            LECTURER_REPOSITORY_MAP.put(lecturerId, lecturer);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student, int studentId) {
        if (STUDENT_REPOSITORY_MAP.containsKey(studentId)) {
            student.setStudentId(studentId);
            STUDENT_REPOSITORY_MAP.put(studentId, student);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course, int courseId) {
        if (COURSE_REPOSITORY_MAP.containsKey(courseId)) {
            course.setCourseId(courseId);
            COURSE_REPOSITORY_MAP.put(courseId, course);
            //CLIENT_REPOSITORY_MAP.get(id).getCLIENT_ORDER_MAP().put(id_order, order);
            //CLIENT_REPOSITORY_MAP.get(id).getCLIENT_ORDER_MAP().get(id_order).getORDER_ITEMS_MAP().put(id_item, item);
            return true;
        }
        return false;
    }



    @Override
    public boolean deleteLecturer(int lecturerId) {
        return LECTURER_REPOSITORY_MAP.remove(lecturerId) != null;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return STUDENT_REPOSITORY_MAP.remove(studentId) != null;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        return COURSE_REPOSITORY_MAP.remove(courseId) != null;
    }


}
