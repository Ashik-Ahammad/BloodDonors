package com.example.blooddonors.donorestore;

import android.os.Build;

import com.example.blooddonors.Donor;
import com.example.blooddonors.DonorAdapter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public static void editExistingDonor(String docId,Donor updatedDonor){
        store.editDonor(docId,updatedDonor);
    }

}
