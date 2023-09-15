package com.gelo.student_management.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentSection implements Serializable{

    @Id
    @SequenceGenerator(
        name = "student_section_sequence",
        sequenceName = "student_section_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_section_sequence"
    )
    private Long id;

    @NonNull
    @Column(name = "section")
    private Integer section;

    @NonNull
    @Column(name = "level")
    private Integer level;

    @NonNull 
    @Column(name = "school_year")
    private Integer schoolYear;

    @NonNull
    @OneToMany
    private List<Student> students;
}
