package com.example.blooddonors;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonors.donorestore.DonorDataListener;
import com.example.blooddonors.donorestore.DonorStoreImpl;
import com.example.blooddonors.donorestore.DonorStoreManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addDonorBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDonorBtn = findViewById(R.id.add_donor_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);

        addDonorBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, DonorDetailsActivity.class)));
        menuBtn.setOnClickListener((v)->showMenu());
        setupRecyclerView();
    }

    void showMenu(){
        PopupMenu popupMenu  = new PopupMenu(MainActivity.this,menuBtn);
        popupMenu.getMenu().add("Logout");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getTitle()=="Logout"){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    void setupRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DonorAdapter adapter = new DonorAdapter(getApplicationContext());
        DonorStoreImpl.getInstance().getAllDonors(new DonorDataListener() {
            @Override
            public void onDonorsFetched(List<Donor> donors) {
                adapter.setDonors(donors);
                adapter.notifyDataSetChanged();
            }
        });
//        adapter.setDonors(getDummyDonors());
        recyclerView.setAdapter(adapter);
    }
     List<Donor> getDummyDonors(){
        List<Donor> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Donor donor = new Donor();
            donor.setName(UUID.randomUUID().toString());
            donor.setPhone(UUID.randomUUID().toString());
            donor.setDepartment(UUID.randomUUID().toString());
            donor.setTimestamp(Timestamp.now());
            donor.setBloodGrp("B+");
            donor.setAddress("Dhaka");
            list.add(donor);
        }
        return list;
    }
}