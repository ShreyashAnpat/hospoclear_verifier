package com.e.hospoclear.model;

public class hospitalData {

   private String HospitalName ,ContactNumber , City , State ;

    public hospitalData(String hospitalName, String contactNumber, String city, String state) {
        HospitalName = hospitalName;
        ContactNumber = contactNumber;
        City = city;
        State = state;

    }

    public hospitalData() {
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
