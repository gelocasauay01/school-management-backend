package com.gelo.student_management.service;

import java.text.ParseException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.TeacherDTO;
import com.gelo.student_management.model.PersonalData;
import com.gelo.student_management.model.Teacher;
import com.gelo.student_management.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private PersonalDataService personalDataService;

    public Teacher createTeacher(TeacherDTO newTeacherDTO) throws ParseException {
        PersonalData newPersonalData = personalDataService.createPersonalData(newTeacherDTO.getPersonalData());
        Teacher newTeacher = new Teacher(newPersonalData);
        return teacherRepository.save(newTeacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow();
    }

    public Teacher updateTeacher(Long id, TeacherDTO updatedTeacherDTO) throws ParseException {
        PersonalData updatePersonalData = personalDataService.updatePersonalData(updatedTeacherDTO.getPersonalData().getId(), updatedTeacherDTO.getPersonalData());
        Teacher updatedTeacher = new Teacher(updatePersonalData);     
        updatedTeacher.setId(id);
        return teacherRepository.save(updatedTeacher);
    }

    public void deleteAllTeachers() {
        teacherRepository.deleteAll();
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    } 
}
