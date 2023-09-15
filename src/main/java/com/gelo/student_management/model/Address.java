package com.gelo.student_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Entity(name = "Address")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @SequenceGenerator(
        name = "address_sequence",
        sequenceName = "address_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "address_sequence"
    )
    private Long id;

    @NonNull 
    @Column(name = "HouseNumber")
    private Integer houseNumber;

    @NonNull 
    @Column(name = "Street")
    private String street;

    @NonNull 
    @Column(name = "Subdivision")
    private String subdivision;

    @NonNull 
    @Column(name = "City")
    private String city;

    @NonNull 
    @Column(name = "Province")
    private String province;
}  
