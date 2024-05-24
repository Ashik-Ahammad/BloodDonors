package com.example.blooddonors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.blooddonors.donorestore.DonorStoreImpl;
import com.example.blooddonors.donorestore.DonorStoreManager;
import com.example.blooddonors.donorestore.GetDonorCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.util.Util;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DonorDetailsActivity extends AppCompatActivity {

    EditText nameEditText,bloodGrpEditText,deptEditText,addressEditText,phoneEditText,lastDonationDateEditText;
    TextView pageTitle;
    ImageButton saveDonorBtn;
    String existingDonorDocId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);

        Intent intent = getIntent();
        boolean edit_flag = Objects.requireNonNull(intent.getExtras()).getBoolean("edit");
        pageTitle = findViewById(R.id.page_title);
        nameEditText = findViewById(R.id.donor_name_text);
        bloodGrpEditText = findViewById(R.id.donor_bg_text);
        deptEditText = findViewById(R.id.donor_dept_text);
        addressEditText = findViewById(R.id.donor_address_text);
        phoneEditText = findViewById(R.id.donor_number_text);
        lastDonationDateEditText = findViewById(R.id.date_input);
        saveDonorBtn = findViewById(R.id.save_donor_btn);

        if(edit_flag){
            existingDonorDocId = intent.getStringExtra("doc_id");
            pageTitle.setText("Edit your enlistment");

            DonorStoreImpl.getInstance().getDonor(existingDonorDocId, new GetDonorCallback() {
                @Override
                public void onDonorFetched(Donor donor) {
                    nameEditText.setHint(donor.getName());
                    bloodGrpEditText.setHint(donor.getBloodGrp());
                    deptEditText.setHint(donor.getDepartment());
                    lastDonationDateEditText.setHint(donor.getLastDonationDate());
                    addressEditText.setHint(donor.getAddress());
                    phoneEditText.setHint(donor.getPhone());
                }
            });
        }
        saveDonorBtn.setOnClickListener((v)-> saveDonor(edit_flag));
    }

    void saveDonor(boolean existingDonor) {
        String donorName = nameEditText.getText().toString();
        String donorBG = bloodGrpEditText.getText().toString();
        String donorDept = deptEditText.getText().toString();
        String donorAddress = addressEditText.getText().toString();
        String donorPhone = phoneEditText.getText().toString();
        String donationDate = lastDonationDateEditText.getText().toString();

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
        } else if (donationDate == null || donationDate.isEmpty()) {
            lastDonationDateEditText.setError(("Last date of donation is required!"));
        }

        //create new donor
        Donor donor = new Donor();
        donor.setDonorId(Utility.getCurrentUser().getUid());
        donor.setName(donorName);
        donor.setBloodGrp(donorBG);
        donor.setDepartment(donorDept);
        donor.setLastDonationDate(donationDate);
        donor.setAddress(donorAddress);
        donor.setPhone(donorPhone);
        donor.setTimestamp(Timestamp.now());

        if(existingDonor) DonorStoreImpl.getInstance().editDonor(existingDonorDocId,donor);
        else DonorStoreManager.saveDonor(donor);
        /**
         * Toast here if needed.
         */
        Utility.showToast(this,"Donor Added Successfully");
        finish();
    }
}