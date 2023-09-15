package com.gelo.student_management.service;

import java.text.ParseException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.StudentDTO;
import com.gelo.student_management.model.PersonalData;
import com.gelo.student_management.model.Student;
import com.gelo.student_management.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired private StudentRepository studentRepository;
    @Autowired private PersonalDataService personalDataService;

    public Student createStudent(StudentDTO newStudentDTO) throws ParseException {
        PersonalData newPersonalData = personalDataService.createPersonalData(newStudentDTO.getPersonalData());
        Student newStudent = new Student(newPersonalData);
        return studentRepository.save(newStudent);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsById(List<Long> ids) {
        return studentRepository.findAllById(ids);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student updateStudent(Long id, StudentDTO updatedStudentDTO) throws ParseException {
        PersonalData updatePersonalData = personalDataService.updatePersonalData(updatedStudentDTO.getPersonalData().getId(), updatedStudentDTO.getPersonalData());
        Student updatedStudent = new Student(updatePersonalData);     
        updatedStudent.setId(id);
        return studentRepository.save(updatedStudent);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    } 
}
