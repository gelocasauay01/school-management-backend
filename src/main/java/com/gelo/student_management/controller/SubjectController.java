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

import com.gelo.student_management.dto.SubjectDTO;
import com.gelo.student_management.model.Subject;
import com.gelo.student_management.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    private ResponseEntity<Subject> createSubject(@RequestBody SubjectDTO newSubjectDTO) {
        ResponseEntity<Subject> response;
        try {
            response = new ResponseEntity<Subject>(subjectService.createSubject(newSubjectDTO), HttpStatus.ACCEPTED);
        } catch (ParseException parseException) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    private ResponseEntity<List<Subject>> getAllSubjects() {
        ResponseEntity<List<Subject>> response;
        try {
            response = new ResponseEntity<List<Subject>>(subjectService.getAllSubjects(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{id}") 
    private ResponseEntity<Subject> getSubject(@PathVariable("id") Long id) {
        ResponseEntity<Subject> response;
        try {
            response = new ResponseEntity<Subject>(subjectService.getSubject(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/{id}") 
    private ResponseEntity<Subject> updateSubject(@PathVariable("id") Long id, @RequestBody SubjectDTO updatedSubjectDTO) {
        ResponseEntity<Subject> response;
        try {
            response = new ResponseEntity<Subject>(subjectService.updateSubject(id, updatedSubjectDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/all")
    private ResponseEntity<Boolean> deleteAllSubjects() {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            subjectService.deleteAllSubjects();
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteSubject(@PathVariable("id") Long id) {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            subjectService.deleteSubject(id);
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }
}
