package com.example.gauravgupta.XceloreHospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gauravgupta.XceloreHospital.entity.Doctor;
import com.example.gauravgupta.XceloreHospital.entity.Speciality;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialityAndCity(Speciality speciality, String city);
}
