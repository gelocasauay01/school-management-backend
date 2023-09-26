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

import com.gelo.student_management.dao.FinalGrade;
import com.gelo.student_management.dao.StudentRecordDao;
import com.gelo.student_management.dto.StudentRecordDTO;
import com.gelo.student_management.service.StudentRecordService;

@RestController
@RequestMapping("/api/student-record")
public class StudentRecordController {
    
    @Autowired
    private StudentRecordService studentRecordService;

    @PostMapping
    private ResponseEntity<StudentRecordDao> createStudentRecord(@RequestBody StudentRecordDTO newStudentRecordDTO) {
        ResponseEntity<StudentRecordDao> response;
        try {
            response = new ResponseEntity<StudentRecordDao>(studentRecordService.createStudentRecord(newStudentRecordDTO), HttpStatus.ACCEPTED);
        } catch (ParseException parseException) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    private ResponseEntity<List<StudentRecordDao>> getAllStudentRecords() {
        ResponseEntity<List<StudentRecordDao>> response;
        try {
            response = new ResponseEntity<List<StudentRecordDao>>(studentRecordService.getAllStudentRecords(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{id}")
    private ResponseEntity<StudentRecordDao> getStudentRecord(@PathVariable("id") Long id) {
        ResponseEntity<StudentRecordDao> response;
        try {
            response = new ResponseEntity<StudentRecordDao>(studentRecordService.getStudentRecord(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{subject-class-id}/{student-id}")
    private ResponseEntity<List<StudentRecordDao>> getStudentRecordsBySubjectClassIdAndStudentId(@PathVariable("subject-class-id") Long subjectClassId, @PathVariable("student-id") Long studentId) {
        ResponseEntity<List<StudentRecordDao>> response;
        try {
            response = new ResponseEntity<List<StudentRecordDao>>(studentRecordService.getStudentRecordsBySubjectClassIdAndStudentId(subjectClassId, studentId), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/{subject-class-id}/{student-id}/final-grade")
    private ResponseEntity<FinalGrade> getFinalGradeBySubjectClassIdAndStudentId(@PathVariable("subject-class-id") Long subjectClassId, @PathVariable("student-id") Long studentId) {
        ResponseEntity<FinalGrade> response;
        try {
            response = new ResponseEntity<FinalGrade>(studentRecordService.getFinalGradeBySubjectClassIdAndStudentId(subjectClassId, studentId), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/{id}") 
    private ResponseEntity<StudentRecordDao> updateStudentRecord(@PathVariable("id") Long id, @RequestBody StudentRecordDTO updatedStudentRecordDTO) {
        ResponseEntity<StudentRecordDao> response;
        try {
            response = new ResponseEntity<StudentRecordDao>(studentRecordService.updateStudentRecord(id, updatedStudentRecordDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/all")
    private ResponseEntity<Boolean> deleteAllStudentRecords() {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            studentRecordService.deleteAllStudentRecords();
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteStudentRecord(@PathVariable("id") Long id) {
        Boolean isSuccessful = true;
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        try {
            studentRecordService.deleteStudentRecord(id);
        } catch(Exception e) {
            isSuccessful = false;
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(isSuccessful, statusCode);
    }
}
