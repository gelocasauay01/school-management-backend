package com.gelo.student_management.service;

import java.text.ParseException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.StudentSectionDTO;
import com.gelo.student_management.model.Student;
import com.gelo.student_management.model.StudentSection;
import com.gelo.student_management.repository.StudentSectionRepository;

@Service
public class StudentSectionService {

    @Autowired private StudentSectionRepository studentSectionRepository;
    @Autowired private StudentService studentService;

    public StudentSection createStudentSection(StudentSectionDTO newStudentSectionDTO) throws ParseException {
        StudentSection newStudentSection = convertDTO(newStudentSectionDTO);
        return studentSectionRepository.save(newStudentSection);
    }

    public List<StudentSection> getAllStudentSections() {
        return studentSectionRepository.findAll();
    }

    public StudentSection getStudentSection(Long id) {
        return studentSectionRepository.findById(id).orElseThrow();
    }

    public StudentSection updateStudentSection(Long id, StudentSectionDTO updatedStudentSectionDTO) throws ParseException {
        StudentSection updatedStudentSection = convertDTO(updatedStudentSectionDTO);    
        updatedStudentSection.setId(id);
        return studentSectionRepository.save(updatedStudentSection);
    }

    public void deleteAllStudentSections() {
        studentSectionRepository.deleteAll();
    }

    public void deleteStudentSection(Long id) {
        studentSectionRepository.deleteById(id);
    } 

    private StudentSection convertDTO(StudentSectionDTO studentSectionDTO) {
        List<Student> students = studentService.getStudentsById(studentSectionDTO.getStudents());
        return new StudentSection(
            studentSectionDTO.getSection(),
            studentSectionDTO.getLevel(),
            studentSectionDTO.getSchoolYear(),
            students
        );
    }
}
