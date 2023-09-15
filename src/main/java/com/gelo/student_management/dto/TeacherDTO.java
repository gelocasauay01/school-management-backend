package com.gelo.student_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TeacherDTO {
    private Long id;
    private PersonalDataDTO personalData;
}
