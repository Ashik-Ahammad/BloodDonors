package com.example.blooddonors;

import com.google.firebase.Timestamp;

public class Donor {
    String name;
    String bloodGrp;
    String department;
    String address;
    String phone;
    Timestamp timestamp;

    public Donor() {

    }


    //name get & set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //bloodGroup get & set
    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    //bloodGroup get & set
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //address get & set
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //phone get & set
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //time get & set
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
