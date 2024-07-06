package com.example.gauravgupta.XceloreHospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gauravgupta.XceloreHospital.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
