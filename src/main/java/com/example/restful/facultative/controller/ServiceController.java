package com.example.restful.facultative.controller;

import com.example.restful.facultative.model.Course;
import com.example.restful.facultative.model.Lecturer;
import com.example.restful.facultative.model.Student;
import com.example.restful.facultative.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {


    private final EducationService educationService;
    @Autowired
    public ServiceController(EducationService educationService) {
        this.educationService = educationService;
    }

    //post

     @PostMapping(value = "/lecturer")
    public ResponseEntity<?> create(@RequestBody Lecturer lecturer) {
        educationService.createLecturer(lecturer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<?> create(@RequestBody Student student) {
        educationService.createStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/course")
    public ResponseEntity<?> create(@RequestBody Course course) {
        educationService.createCourse(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //get

    @GetMapping(value = "/lecturers")
    public ResponseEntity<List<Lecturer>> readLecturers() {
        final List<Lecturer> lecturers = educationService.readAllLecturers();

        return lecturers != null && !lecturers.isEmpty()
                ? new ResponseEntity<>(lecturers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/lecturers/{lecturerId}")
    public ResponseEntity<Lecturer> readLecturers(@PathVariable(name = "lecturerId") int lecturerId) {
        final Lecturer lecturer = educationService.readLecturer(lecturerId);

        return lecturer != null
                ? new ResponseEntity<>(lecturer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> readStudents() {
        final List<Student> students = educationService.readAllStudents();

        return students != null && !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/students/{studentId}")
    public ResponseEntity<Student> readStudents(@PathVariable(name = "studentId") int studentId) {
        final Student student = educationService.readStudent(studentId);

        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/courses")
    public ResponseEntity<List<Course>> readCourses() {
        final List<Course> courses = educationService.readAllCourses();

        return courses != null && !courses.isEmpty()
                ? new ResponseEntity<>(courses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/courses/{courseId}")
    public ResponseEntity<Course> readCourses(@PathVariable(name = "courseId") int courseId) {
        final Course course = educationService.readCourse(courseId);

        return course != null
                ? new ResponseEntity<>(course, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/courses/{courseId}/students/{studentId}/estimate")
    public ResponseEntity<Integer> readEstimate(@PathVariable(name = "courseId") int courseId,@PathVariable(name = "studentId") int studentId) {
        final Integer estimate = educationService.readEstimate(courseId,studentId);

        return estimate != null
                ? new ResponseEntity<>(estimate, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/courses/{courseId}/students")
    public ResponseEntity<List<Student>> readStudentsInCourse(@PathVariable(name = "courseId") int courseId) {
        final List<Student> students = educationService.readStudentsInCourse(courseId);

        return students != null
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //put

    @PutMapping(value = "/lecturers/{lecturerId}")
    public ResponseEntity<?> updateLecturer(@PathVariable(name = "lecturerId") int lecturerId, @RequestBody Lecturer lecturer) {
        final boolean updated = educationService.updateLecturer(lecturer, lecturerId);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/students/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable(name = "studentId") int studentId, @RequestBody Student student) {
        final boolean updated = educationService.updateStudent(student, studentId);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/courses/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable(name = "courseId") int courseId, @RequestBody Course course) {
        final boolean updated = educationService.updateCourse(course, courseId);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //delete

    @DeleteMapping(value = "/lecturers/{lecturerId}")
    public ResponseEntity<?> deleteLecturer(@PathVariable(name = "lecturerId") int lecturerId) {
        final boolean deleted = educationService.deleteLecturer(lecturerId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable(name = "studentId") int studentId) {
        final boolean deleted = educationService.deleteStudent(studentId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}


