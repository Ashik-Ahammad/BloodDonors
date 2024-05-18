package com.example.blooddonors.donorestore;

import android.util.Log;

import com.example.blooddonors.Donor;
import com.example.blooddonors.DonorAdapter;
import com.example.blooddonors.Utility;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DonorStoreImpl implements DonorStore {
    private static DonorStoreImpl instance;
    public static DonorStoreImpl getInstance(){
        if(instance == null){
            instance = new DonorStoreImpl();
        }
        return instance;
    }
    @Override
    public void saveDonor(Donor donor) {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForDonors().document();
        documentReference.set(donor).addOnCompleteListener(task -> {

        if(task.isSuccessful())Log.i("a","Donor saved");
        else if (task.isCanceled()) Log.i("a","Couldn't save donor");
        });
    }

    @Override
    public Donor getDonor(String key) {
        return null;
    }

    @Override
    public void getAllDonors(DonorDataListener listener) {
        CollectionReference reference = Utility.getCollectionReferenceForDonors();
        reference.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                // Handle error (optional)
                return;
            }

            if (snapshot != null) {
                List<Donor> donors = new ArrayList<>();
                for (QueryDocumentSnapshot doc : snapshot) {
                    donors.add(doc.toObject(Donor.class));
                }
                listener.onDonorsFetched(donors);
            }
        });
    }
}
