package com.example.blooddonors;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonors.donorestore.DonorStoreManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

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

        List<Donor> donors = DonorStoreManager.getAllDonorDeets();
        DonorAdapter adapter = new DonorAdapter(donors,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}