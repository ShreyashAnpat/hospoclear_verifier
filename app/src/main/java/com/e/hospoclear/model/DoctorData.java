package com.e.hospoclear.model;

public class DoctorData {
    private String DoctorName , Experience , Qualification ,Speciality ;

    public String getDoctorName() {
        return DoctorName;
    }

    public DoctorData(String doctorName, String experience, String qualification, String speciality) {
        DoctorName = doctorName;
        Experience = experience;
        Qualification = qualification;
        Speciality = speciality;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public DoctorData() {

    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }
}
