package com.gelo.student_management.model;

import java.io.Serializable;

import com.gelo.student_management.enums.RecordType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.NonNull;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StudentRecord implements Serializable{
    @Id
    @SequenceGenerator(
        name = "student_record_sequence",
        sequenceName = "student_record_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_record_sequence"
    )
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "subject_class_id", referencedColumnName = "id")
    private SubjectClass subjectClass;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "record_type")
    private RecordType recordType;

    @NonNull
    private Double scorePercentage;
}
