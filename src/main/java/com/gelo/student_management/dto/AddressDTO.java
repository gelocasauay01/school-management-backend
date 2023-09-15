package com.gelo.student_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddressDTO {
    private Long id;
    private Integer houseNumber;
    private String street;
    private String subdivision;
    private String city;
    private String province;
}
