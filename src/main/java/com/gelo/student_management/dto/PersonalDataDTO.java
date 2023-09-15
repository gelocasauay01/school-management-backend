package com.gelo.student_management.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PersonalDataDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer gender;
    private String birthDate;
    private AddressDTO address;
}
