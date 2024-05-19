package com.example.blooddonors.donorestore;

import com.example.blooddonors.Donor;

import java.util.List;

public interface DonorStore {
    void saveDonor(Donor donor);
    void editDonor(String docId,Donor newDonor);
    void getDonor(String docId,GetDonorCallback callback);
    void getAllDonors(DonorDataListener listener);
    void deleteDonor(String docId,DeletedDonorCallBack cb);
}
