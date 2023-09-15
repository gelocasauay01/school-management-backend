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

import com.gelo.student_management.dto.SubjectClassDTO;
import com.gelo.student_management.model.SubjectClass;
import com.gelo.student_management.service.SubjectClassService;

@RestController
@RequestMapping("/api/subject-class")
public class SubjectClassController {
    
    @Autowired
    private SubjectClassService subjectClassService;

    @PostMapping
    private ResponseEntity<SubjectClass> createSubjectClass(@RequestBody SubjectClassDTO newSubjectClassDTO) {
        ResponseEntity<SubjectClass> response;
        try {
            response = new ResponseEntity<SubjectClass>(subjectClassService.createSubjectClass(newSubjectClassDTO), HttpStatus.ACCEPTED);
        } catch (ParseException parseException) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    private ResponseEntity<List<SubjectClass>> getAllSubjectClasss() {
        ResponseEntity<List<SubjectClass>> response;
        try {
            response = new ResponseEntity<List<SubjectClass>>(subjectClassService.getAllSubjectClasss(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{id}") 
    private ResponseEntity<SubjectClass> getSubjectClass(@PathVariable("id") Long id) {
        ResponseEntity<SubjectClass> response;
        try {
            response = new ResponseEntity<SubjectClass>(subjectClassService.getSubjectClass(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/{id}") 
    private ResponseEntity<SubjectClass> updateSubjectClass(@PathVariable("id") Long id, @RequestBody SubjectClassDTO updatedSubjectClassDTO) {
        ResponseEntity<SubjectClass> response;
        try {
            response = new ResponseEntity<SubjectClass>(subjectClassService.updateSubjectClass(id, updatedSubjectClassDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/all")
    private ResponseEntity<Boolean> deleteAllSubjectClasss() {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            subjectClassService.deleteAllSubjectClasss();
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteSubjectClass(@PathVariable("id") Long id) {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            subjectClassService.deleteSubjectClass(id);
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }
}
