package com.example.beautyhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyhair.adapter.ShopAdapter;
import com.example.beautyhair.data.model.Shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerShopsListingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private ShopAdapter shopAdapter;
    private List<Shop> mShops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_shops_listing);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShops = new ArrayList<>();
        readShops();
    }

    private void readShops(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_shop = database.getReference("Shop");

        table_shop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mShops.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Shop shop = dataSnapshot.getValue(Shop.class);
                    mShops.add(shop);
                }

                shopAdapter = new ShopAdapter(CustomerShopsListingActivity.this, mShops, getIntent().getStringExtra("customer_phone"));
                recyclerView.setAdapter(shopAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}