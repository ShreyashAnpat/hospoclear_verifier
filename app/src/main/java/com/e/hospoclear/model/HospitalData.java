package com.e.hospoclear.model;

public class HospitalData {

   private String HospitalName ,ContactNumber , City , State,HospitalImgUrl,UserId , HospitalEmail ,Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public HospitalData(String hospitalName, String contactNumber, String city, String state, String hospitalImgUrl, String userId , String hospitalEmail , String status) {
        HospitalName = hospitalName;
        ContactNumber = contactNumber;
        City = city;
        State = state;
        HospitalImgUrl = hospitalImgUrl;
        UserId = userId;
        HospitalEmail  = hospitalEmail ;
        Status = status ;
    }

    public HospitalData() {
    }

    public String getHospitalImgUrl() {
        return HospitalImgUrl;
    }

    public void setHospitalImgUrl(String hospitalImgUrl) {
        HospitalImgUrl = hospitalImgUrl;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String hospitalId) {
        UserId = hospitalId;
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
    public String getHospitalEmail() {
        return HospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        HospitalEmail = hospitalEmail;
    }
}
