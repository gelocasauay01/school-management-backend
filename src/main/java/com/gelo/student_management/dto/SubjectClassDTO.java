package com.gelo.student_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubjectClassDTO {
    private Long id;
    private Long studentSectionId;
    private Long teacherId;
    private Long subjectId;
}
