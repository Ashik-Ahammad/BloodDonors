package com.example.blooddonors.donorestore;

import com.example.blooddonors.Donor;
import com.google.firebase.Timestamp;
import android.util.Log;

import junit.framework.TestCase;

public class DonorStoreImplTest extends TestCase {

    public void testSaveDonor() {
//        Donor donor = new Donor();
//        donor.setName("Saadman");
//        donor.setAddress("Dhamrai");
//        donor.setDepartment("CSE");
//        donor.setPhone("01739046386");
//        donor.setTimestamp(Timestamp.now());
//        DonorStoreManager.saveDonor(donor);
        System.out.println("abcd");

    }

    public void testGetAllDonorDeets() {
        for(Donor donor : DonorStoreManager.getAllDonorDeets()){
            System.out.println(donor);
        }
    }
}