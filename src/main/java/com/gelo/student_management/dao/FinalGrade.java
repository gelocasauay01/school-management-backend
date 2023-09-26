package com.gelo.student_management.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FinalGrade {
    private Long subjectClassId;
    private Long studentId;
    private Double finalGrade;
}
