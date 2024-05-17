package com.example.blooddonors.donorestore;

import com.example.blooddonors.Donor;

import java.util.List;

public class DonorStoreManager {
    /**
     * Calls to the store shouldn't be made directly.
     * After the security checks(if there's any) from
     * the Manager class, calls to the store are made by the Manager class.
     */
    static DonorStore store = new DonorStoreImpl();

    public static void saveDonor(Donor donor){
        store.saveDonor(donor);
    }
    public static List<Donor> getAllDonorDeets(){
        return store.getAllDonorDeets();
    }
}
