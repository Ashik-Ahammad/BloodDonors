package com.example.blooddonors;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
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
import com.google.firebase.firestore.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addDonorBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDonorBtn = findViewById(R.id.add_donor_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);
        searchBar = findViewById(R.id.search_bar);

        addDonorBtn.setOnClickListener((v)-> {
            Intent intent = new Intent(MainActivity.this, DonorDetailsActivity.class);
            intent.putExtra("edit",false);
            startActivity(intent);
        });
        menuBtn.setOnClickListener((v)->showMenu());
        setupRecyclerView();
        setupSearchBar();
    }

    void showMenu(){
        PopupMenu popupMenu  = new PopupMenu(MainActivity.this,menuBtn);
        popupMenu.getMenu().add(Utility.getCurrentUser().getEmail());
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
    private void setupSearchBar() {
//        searchBar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Utility.showToast(getApplicationContext(),charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    void setupRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DonorAdapter adapter = new DonorAdapter(this);
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