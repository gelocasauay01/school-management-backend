package com.gelo.student_management.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentSectionDTO {
    private Integer id;
    private Integer section;
    private Integer level;
    private Integer schoolYear;
    private List<Long> students;
}
