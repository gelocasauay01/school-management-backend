package com.gelo.student_management.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentSectionDao {
    private Long id;
    private Integer section;
    private Integer level;
    private Integer schoolYear;
    private List<Long> studentIds;
}
