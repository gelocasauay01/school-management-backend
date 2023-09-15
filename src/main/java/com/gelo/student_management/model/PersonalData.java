package com.gelo.student_management.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import com.gelo.student_management.enums.Gender;


@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PersonalData implements Serializable{
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
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    @NonNull 
    @Column(name = "gender")
    private Gender gender;
    
    @NonNull 
    @Column(name = "birth_date")
    private Date birthDate;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
