package com.gelo.student_management.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.PersonalDataDTO;
import com.gelo.student_management.enums.Gender;
import com.gelo.student_management.model.Address;
import com.gelo.student_management.model.PersonalData;
import com.gelo.student_management.repository.PersonalDataRepository;

@Service
public class PersonalDataService {

    @Autowired private AddressService addressService;
    @Autowired private PersonalDataRepository personalDataRepository;

    public PersonalData createPersonalData(PersonalDataDTO newPersonalDataDTO) throws ParseException {
        Address newAddress = addressService.createAddress(newPersonalDataDTO.getAddress());
        PersonalData newPersonalData = convertDTO(newPersonalDataDTO, newAddress);
        return personalDataRepository.save(newPersonalData);
    }

    public PersonalData updatePersonalData(Long id, PersonalDataDTO updatedPersonalDataDTO) throws ParseException {
        Address newAddress = addressService.updateAddress(updatedPersonalDataDTO.getAddress().getId(), updatedPersonalDataDTO.getAddress());
        PersonalData newPersonalData = convertDTO(updatedPersonalDataDTO, newAddress);
        newPersonalData.setId(id);
        return personalDataRepository.save(newPersonalData);
    }

    private PersonalData convertDTO(PersonalDataDTO  personalDataDTO, Address address) throws ParseException {
        SimpleDateFormat birthDateFormatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        return new PersonalData(
            personalDataDTO.getFirstName(), 
            personalDataDTO.getLastName(), 
            Gender.values()[personalDataDTO.getGender()],
            birthDateFormatter.parse(personalDataDTO.getBirthDate()),
            address
        );
    }
}
