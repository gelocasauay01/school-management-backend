package com.gelo.student_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gelo.student_management.model.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
