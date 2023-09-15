package com.gelo.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gelo.student_management.model.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    
}
