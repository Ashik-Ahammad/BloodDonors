package com.example.blooddonors.donorestore;

import com.example.blooddonors.Donor;

import java.util.List;

public interface DonorDataListener {
    void onDonorsFetched(List<Donor> donors);
}
