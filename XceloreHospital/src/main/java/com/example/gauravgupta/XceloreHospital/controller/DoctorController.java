package com.example.gauravgupta.XceloreHospital.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gauravgupta.XceloreHospital.entity.Doctor;
import com.example.gauravgupta.XceloreHospital.service.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDoctor(@PathVariable Long id) {
        doctorService.removeDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<Doctor>> suggestDoctors(@RequestParam String patientSymptom,
                                                       @RequestParam String patientCity) {
        List<Doctor> suggestedDoctors = doctorService.suggestDoctors(patientSymptom, patientCity);
        return ResponseEntity.ok(suggestedDoctors);
    }
}
