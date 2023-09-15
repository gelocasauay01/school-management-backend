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
import com.gelo.student_management.model.Teacher;
import com.gelo.student_management.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    private ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO newTeacherDTO) {
        ResponseEntity<Teacher> response;
        try {
            response = new ResponseEntity<Teacher>(teacherService.createTeacher(newTeacherDTO), HttpStatus.ACCEPTED);
        } catch (ParseException parseException) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    private ResponseEntity<List<Teacher>> getAllTeachers() {
        ResponseEntity<List<Teacher>> response;
        try {
            response = new ResponseEntity<List<Teacher>>(teacherService.getAllTeachers(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{id}") 
    private ResponseEntity<Teacher> getTeacher(@PathVariable("id") Long id) {
        ResponseEntity<Teacher> response;
        try {
            response = new ResponseEntity<Teacher>(teacherService.getTeacher(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/{id}") 
    private ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody TeacherDTO updatedTeacherDTO) {
        ResponseEntity<Teacher> response;
        try {
            response = new ResponseEntity<Teacher>(teacherService.updateTeacher(id, updatedTeacherDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/all")
    private ResponseEntity<Boolean> deleteAllTeachers() {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            teacherService.deleteAllTeachers();
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteTeacher(@PathVariable("id") Long id) {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            teacherService.deleteTeacher(id);
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }
}
