package com.e.hospoclear.model;

public class hospitalData {

   private String HospitalName ,ContactNumber , City , State,HospitalImgUrl,HospitalId;

    public hospitalData(String hospitalName, String contactNumber, String city, String state,String hospitalImgUrl,String hospitalId) {
        HospitalName = hospitalName;
        ContactNumber = contactNumber;
        City = city;
        State = state;
        HospitalImgUrl = hospitalImgUrl;
        HospitalId = hospitalId;
    }

    public hospitalData() {
    }

    public String getHospitalImgUrl() {
        return HospitalImgUrl;
    }

    public void setHospitalImgUrl(String hospitalImgUrl) {
        HospitalImgUrl = hospitalImgUrl;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String hospitalId) {
        HospitalId = hospitalId;
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
