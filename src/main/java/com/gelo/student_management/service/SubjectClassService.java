package com.gelo.student_management.service;

import java.text.ParseException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.SubjectClassDTO;
import com.gelo.student_management.model.StudentSection;
import com.gelo.student_management.model.Subject;
import com.gelo.student_management.model.SubjectClass;
import com.gelo.student_management.model.Teacher;
import com.gelo.student_management.repository.SubjectClassRepository;

@Service
public class SubjectClassService {

    @Autowired private SubjectClassRepository subjectClassRepository;
    @Autowired private TeacherService teacherService;
    @Autowired private StudentSectionService studentSectionService;
    @Autowired private SubjectService subjectService;

    public SubjectClass createSubjectClass(SubjectClassDTO newSubjectClassDTO) throws ParseException {
        SubjectClass newSubjectClass = convertDTO(newSubjectClassDTO);
        return subjectClassRepository.save(newSubjectClass);
    }

    public List<SubjectClass> getAllSubjectClasss() {
        return subjectClassRepository.findAll();
    }

    public SubjectClass getSubjectClass(Long id) {
        return subjectClassRepository.findById(id).orElseThrow();
    }

    public SubjectClass updateSubjectClass(Long id, SubjectClassDTO updatedSubjectClassDTO) throws ParseException {
        SubjectClass updatedSubjectClass = convertDTO(updatedSubjectClassDTO);
        updatedSubjectClass.setId(id);  
        return subjectClassRepository.save(updatedSubjectClass);
    }

    public void deleteAllSubjectClasss() {
        subjectClassRepository.deleteAll();
    }

    public void deleteSubjectClass(Long id) {
        subjectClassRepository.deleteById(id);
    } 

    private SubjectClass convertDTO(SubjectClassDTO subjectClassDTO) {
        StudentSection studentSection = studentSectionService.getStudentSection(subjectClassDTO.getStudentSectionId());
        Teacher teacher = teacherService.getTeacher(subjectClassDTO.getTeacherId());
        Subject subject = subjectService.getSubject(subjectClassDTO.getSubjectId());
        return new SubjectClass(studentSection, teacher, subject);
    }
}
