package com.example.blooddonors.donorestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.blooddonors.Donor;
import com.example.blooddonors.DonorAdapter;
import com.example.blooddonors.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
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
        donor.setDocId(documentReference.getId());
        documentReference.set(donor).addOnCompleteListener(task -> {

        if(task.isSuccessful())Log.i("a","Donor saved");
        else if (task.isCanceled()) Log.i("a","Couldn't save donor");
        });
    }

    @Override
    public void editDonor(String docId, Donor newDonor) {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForDonors().document(docId);
        newDonor.setDocId(docId);
        documentReference.set(newDonor).addOnCompleteListener(task -> {

            if(task.isSuccessful())Log.i("a","Donor saved");
            else if (task.isCanceled()) Log.i("a","Couldn't save donor");
        });
    }

    @Override
    public void getDonor(String docId,GetDonorCallback callback) {
        CollectionReference reference = Utility.getCollectionReferenceForDonors();
        DocumentReference docReference = reference.document(docId);
        docReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists())
                    {
                        Donor donor = document.toObject(Donor.class);
                        callback.onDonorFetched(donor);
                    }
                }
            }
        });
    }

    @Override
    public void getAllDonors(DonorDataListener listener) {
        CollectionReference reference = Utility.getCollectionReferenceForDonors();
        reference.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                // TODO: Handle error (optional)
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

    @Override
    public void deleteDonor(String docId, DeletedDonorCallBack cb) {
        CollectionReference collectionReference = Utility.getCollectionReferenceForDonors();
        DocumentReference docReference = collectionReference.document(docId);
        docReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                cb.onDeletedDonor(task.isSuccessful());
            }
        });
    }
}
