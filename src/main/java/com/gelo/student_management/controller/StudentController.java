package com.gelo.student_management.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gelo.student_management.dto.TeacherDTO;
import com.gelo.student_management.model.Student;
import com.gelo.student_management.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping
    private ResponseEntity<Student> createStudent(@RequestBody TeacherDTO newStudentDTO) {
        ResponseEntity<Student> response;
        try {
            response = new ResponseEntity<Student>(studentService.createStudent(newStudentDTO), HttpStatus.ACCEPTED);
        } catch (ParseException parseException) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    private ResponseEntity<List<Student>> getAllStudents() {
        ResponseEntity<List<Student>> response;
        try {
            response = new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{id}") 
    private ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        ResponseEntity<Student> response;
        try {
            response = new ResponseEntity<Student>(studentService.getStudent(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/{id}") 
    private ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody TeacherDTO updatedStudentDTO) {
        ResponseEntity<Student> response;
        try {
            response = new ResponseEntity<Student>(studentService.updateStudent(id, updatedStudentDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/all")
    private ResponseEntity<Boolean> deleteAllStudents() {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            studentService.deleteAllStudents();
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteStudent(@PathVariable("id") Long id) {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            studentService.deleteStudent(id);
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }
}
