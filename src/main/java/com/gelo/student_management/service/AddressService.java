package com.gelo.student_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dto.AddressDTO;
import com.gelo.student_management.model.Address;
import com.gelo.student_management.repository.AddressRepository;

@Service
public class AddressService {
    @Autowired private AddressRepository addressRepository;

    public Address createAddress(AddressDTO newAddressDTO) {
        Address newAddress = convertDTO(newAddressDTO);
        return addressRepository.save(newAddress);
    }

    public Address updateAddress(Long id, AddressDTO newAddressDTO) {
        Address updatedAddress = convertDTO(newAddressDTO);
        updatedAddress.setId(id);
        return addressRepository.save(updatedAddress);
    }

    private Address convertDTO(AddressDTO addressDTO) {
        return new Address(
            addressDTO.getHouseNumber(), 
            addressDTO.getStreet(),
            addressDTO.getSubdivision(),
            addressDTO.getCity(),
            addressDTO.getProvince()
        );
    }
}
