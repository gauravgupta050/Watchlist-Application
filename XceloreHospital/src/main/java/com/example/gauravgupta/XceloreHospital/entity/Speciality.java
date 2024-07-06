package com.example.gauravgupta.XceloreHospital.entity;

public enum Speciality {
    ORTHOPAEDIC("Orthopaedic"),
    GYNECOLOGY("Gynecology"),
    DERMATOLOGY("Dermatology"),
    ENT("ENT Specialist");

    private String displayName;

    Speciality(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
