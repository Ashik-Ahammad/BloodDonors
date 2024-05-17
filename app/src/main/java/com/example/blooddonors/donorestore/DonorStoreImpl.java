package com.example.blooddonors.donorestore;

import com.example.blooddonors.Donor;
import com.example.blooddonors.Utility;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DonorStoreImpl implements DonorStore {

    @Override
    public void saveDonor(Donor donor) {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForDonors().document();
        documentReference.set(donor);
    }

    @Override
    public Donor getDonor(String key) {
        return null;
    }

    @Override
    public List<Donor> getAllDonorDeets() {
        List<Donor> donors = new ArrayList<>();
        CollectionReference reference = Utility.getCollectionReferenceForDonors();
        reference.get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               for(QueryDocumentSnapshot doc : task.getResult()){
                   donors.add(doc.toObject(Donor.class));
               }
           }
        });
        return donors;
    }
}
