package com.example.blooddonors;

import com.google.firebase.Timestamp;

public class Donor {
    String docId;
    String donorId;
    String name;
    String bloodGrp;
    String department;
    String address;
    String lastDonationDate;
    String phone;
    Timestamp timestamp;
    public Donor() {

    }

    public Donor(String docId,String donorId, String name, String bloodGrp, String department, String address, String phone, String lastDonationDate, Timestamp timestamp) {
        this.docId = docId;
        this.donorId = donorId;
        this.name = name;
        this.bloodGrp = bloodGrp;
        this.department = department;
        this.address = address;
        this.phone = phone;
        this.lastDonationDate = lastDonationDate;
        this.timestamp = timestamp;
    }
//name get & set

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

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

    //lastDate get set
    public String getLastDonationDate (){ return lastDonationDate; }
    public void setLastDonationDate (String lastDonationDate){this.lastDonationDate = lastDonationDate; }

    //time get & set
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
