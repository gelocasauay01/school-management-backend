package com.gelo.student_management.dao;

import com.gelo.student_management.model.Student;
import com.gelo.student_management.model.SubjectClass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FinalGrade {
    private final SubjectClass subjectClass;
    private final Student student;
    private final Double finalGrade;
}
