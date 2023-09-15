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

import com.gelo.student_management.dto.StudentSectionDTO;
import com.gelo.student_management.model.StudentSection;
import com.gelo.student_management.service.StudentSectionService;

@RestController
@RequestMapping("/api/student-section")
public class StudentSectionController {
    
    @Autowired
    private StudentSectionService studentSectionService;

    @PostMapping
    private ResponseEntity<StudentSection> createStudentSection(@RequestBody StudentSectionDTO newStudentSectionDTO) {
        ResponseEntity<StudentSection> response;
        try {
            response = new ResponseEntity<StudentSection>(studentSectionService.createStudentSection(newStudentSectionDTO), HttpStatus.ACCEPTED);
        } catch (ParseException parseException) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    private ResponseEntity<List<StudentSection>> getAllStudentSections() {
        ResponseEntity<List<StudentSection>> response;
        try {
            response = new ResponseEntity<List<StudentSection>>(studentSectionService.getAllStudentSections(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{id}") 
    private ResponseEntity<StudentSection> getStudentSection(@PathVariable("id") Long id) {
        ResponseEntity<StudentSection> response;
        try {
            response = new ResponseEntity<StudentSection>(studentSectionService.getStudentSection(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/{id}") 
    private ResponseEntity<StudentSection> updateStudentSection(@PathVariable("id") Long id, @RequestBody StudentSectionDTO updatedStudentSectionDTO) {
        ResponseEntity<StudentSection> response;
        try {
            response = new ResponseEntity<StudentSection>(studentSectionService.updateStudentSection(id, updatedStudentSectionDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/all")
    private ResponseEntity<Boolean> deleteAllStudentSections() {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            studentSectionService.deleteAllStudentSections();
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteStudentSection(@PathVariable("id") Long id) {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            studentSectionService.deleteStudentSection(id);
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }
}
