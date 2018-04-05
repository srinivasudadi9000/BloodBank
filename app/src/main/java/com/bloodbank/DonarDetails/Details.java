package com.bloodbank.DonarDetails;

/**
 * Created by DELL on 20-02-2018.
 */

public class Details {
    String id;
    String name;
    String email;
    String number;
    String password1;
    String blood;
    String pincode,aadhar;
    Double latitude,longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Details(String id, String name, String email, String number, String password, String blood, String pincode, String aadhar,
                   Double latitude,Double longitude) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.password1 = password;this.latitude = latitude;
        this.blood = blood; this.longitude = longitude;
        this.pincode = pincode;this.aadhar = aadhar;
    }

    public Details() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
