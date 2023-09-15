package com.gelo.student_management.service;

import java.text.ParseException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.SubjectDTO;
import com.gelo.student_management.model.Subject;
import com.gelo.student_management.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired private SubjectRepository subjectRepository;

    public Subject createSubject(SubjectDTO newSubjectDTO) throws ParseException {
        Subject newSubject = new Subject();
        newSubject.setName(newSubjectDTO.getName()); 
        return subjectRepository.save(newSubject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow();
    }

    public Subject updateSubject(Long id, SubjectDTO updatedSubjectDTO) throws ParseException {
        Subject updatedSubject = new Subject();
        updatedSubject.setId(id);
        updatedSubject.setName(updatedSubjectDTO.getName());     
        return subjectRepository.save(updatedSubject);
    }

    public void deleteAllSubjects() {
        subjectRepository.deleteAll();
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    } 
}
