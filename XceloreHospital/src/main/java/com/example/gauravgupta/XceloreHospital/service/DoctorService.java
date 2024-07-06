package com.example.gauravgupta.XceloreHospital.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gauravgupta.XceloreHospital.entity.Doctor;
import com.example.gauravgupta.XceloreHospital.entity.Speciality;
import com.example.gauravgupta.XceloreHospital.repository.DoctorRepository;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void removeDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> suggestDoctors(String patientSymptom, String patientCity) {
       
        Speciality speciality = determineSpeciality(patientSymptom);
        return doctorRepository.findBySpecialityAndCity(speciality, patientCity);
    }

    
    private Speciality determineSpeciality(String patientSymptom) {
        Speciality speciality = null;
        switch (patientSymptom.toLowerCase()) {
            case "arthritis":
            case "back pain":
            case "tissue injuries":
                speciality = Speciality.ORTHOPAEDIC;
                break;
            case "dysmenorrhea":
                speciality = Speciality.GYNECOLOGY;
                break;
            case "skin infection":
            case "skin burn":
                speciality = Speciality.DERMATOLOGY;
                break;
            case "ear pain":
                speciality = Speciality.ENT;
                break;
            default:
                throw new IllegalArgumentException("Unknown symptom: " + patientSymptom);
        }
        return speciality;
    }
}
