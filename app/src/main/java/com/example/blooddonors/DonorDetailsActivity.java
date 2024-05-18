package com.example.blooddonors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.blooddonors.donorestore.DonorStoreManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.util.Util;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class DonorDetailsActivity extends AppCompatActivity {

    EditText nameEditText,bloodGrpEditText,deptEditText,addressEditText,phoneEditText;
    ImageButton saveDonorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);

        nameEditText = findViewById(R.id.donor_name_text);
        bloodGrpEditText = findViewById(R.id.donor_bg_text);
        deptEditText = findViewById(R.id.donor_dept_text);
        addressEditText = findViewById(R.id.donor_address_text);
        phoneEditText = findViewById(R.id.donor_number_text);
        saveDonorBtn = findViewById(R.id.save_donor_btn);

        saveDonorBtn.setOnClickListener((v)-> saveDonor());
    }

    void saveDonor() {
        String donorName = nameEditText.getText().toString();
        String donorBG = bloodGrpEditText.getText().toString();
        String donorDept = deptEditText.getText().toString();
        String donorAddress = addressEditText.getText().toString();
        String donorPhone = phoneEditText.getText().toString();

        if (donorName == null || donorName.isEmpty()) {
            nameEditText.setError("Donors name is required!");
            return;
        } else if (donorBG == null || donorBG.isEmpty()) {
            bloodGrpEditText.setError("Blood Group is required!");
            return;
        } else if (donorDept == null || donorDept.isEmpty()) {
            deptEditText.setError("Blood Department is required!");
            return;
        } else if (donorAddress == null || donorAddress.isEmpty()) {
            addressEditText.setError("Address is required!");
            return;
        } else if (donorPhone == null || donorPhone.isEmpty()) {
            phoneEditText.setError("Phone number is required!");
            return;
        }

        //create new donor
        Donor donor = new Donor();
        donor.setName(donorName);
        donor.setBloodGrp(donorBG);
        donor.setDepartment(donorDept);
        donor.setAddress(donorAddress);
        donor.setPhone(donorPhone);
        donor.setTimestamp(Timestamp.now());

        DonorStoreManager.saveDonor(donor);
        /**
         * Toast here if needed.
         */
        finish();
    }
}