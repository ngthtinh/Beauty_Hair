package com.example.beautyhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyhair.data.model.Shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class CustomerBookingActivity extends AppCompatActivity {
    TextView textShopName;
    TextView textShopAdress;
    TextView textShopPhone;
    TextView textShopDescription;
    TextView textShopRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking);

        textShopName = findViewById(R.id.textShopName);
        textShopAdress = findViewById(R.id.textShopAddress);
        textShopPhone = findViewById(R.id.textShopPhone);
        textShopDescription = findViewById(R.id.textShopDescription);
        textShopRate = findViewById(R.id.textShopRate);

        String shop_phone = getIntent().getStringExtra("shop_phone");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_shop = database.getReference("Shop");

        table_shop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shop shop = snapshot.child(shop_phone).getValue(Shop.class);

                textShopName.setText(shop.getName());
                textShopAdress.setText(shop.getAddress());
                textShopPhone.setText("Số điện thoại: " + shop.getPhone());
                textShopDescription.setText(shop.getDescription());
                textShopRate.setText(Float.toString(shop.getRate()) + "/5.0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}