package com.gelo.student_management.model;

import java.io.Serializable;

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

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable{
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
    @Column(name = "house_number")
    private Integer houseNumber;

    @NonNull 
    @Column(name = "street")
    private String street;

    @NonNull 
    @Column(name = "subdivision")
    private String subdivision;

    @NonNull 
    @Column(name = "city")
    private String city;

    @NonNull 
    @Column(name = "province")
    private String province;
}  
