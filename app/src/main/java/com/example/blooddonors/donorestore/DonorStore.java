package com.example.blooddonors.donorestore;

import com.example.blooddonors.Donor;

import java.util.List;

public interface DonorStore {
    void saveDonor(Donor donor);
    Donor getDonor(String key);
    List<Donor> getAllDonorDeets();
}
