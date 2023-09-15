package com.gelo.student_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StudentDTO {
    private Long id;
    private PersonalDataDTO personalData;
}
