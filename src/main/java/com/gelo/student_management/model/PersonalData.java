package com.gelo.student_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.gelo.student_management.enums.Gender;


@Entity(name = "PersonalData")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PersonalData {
    @Id
    @SequenceGenerator(
        name = "personal_sequence",
        sequenceName = "personal_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "personal_sequence"
    )
    private Long id;
    
    @NonNull 
    @Column(name = "FirstName")
    private String firstName;

    @NonNull
    @Column(name = "LastName")
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    @NonNull 
    @Column(name = "Gender")
    private Gender gender;
    
    @NonNull 
    @Column(name = "BirthDate")
    private Date birthDate;

    @OneToOne
    @NonNull 
    @PrimaryKeyJoinColumn
    private Address address;
}
