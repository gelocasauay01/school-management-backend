package com.gelo.student_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentRecordDTO {
    private Long id;
    private Long studentId;
    private Long subjectClassId;
    private Integer recordType;
    private Double scorePercentage;
}
