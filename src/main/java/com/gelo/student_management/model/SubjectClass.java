package com.gelo.student_management.model;

import lombok.NonNull;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SubjectClass implements Serializable{
    @Id
    @SequenceGenerator(
        name = "subject_class_sequence",
        sequenceName = "subject_class_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "subject_class_sequence"
    )
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "StudentSectionId", referencedColumnName = "id")
    private StudentSection studentSection;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "TeacherId", referencedColumnName = "id")
    private Teacher teacher;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "SubjectId", referencedColumnName = "id")
    private Subject subject;
}
