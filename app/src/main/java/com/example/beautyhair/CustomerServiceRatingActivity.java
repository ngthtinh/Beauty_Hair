package com.example.beautyhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyhair.data.model.Order;
import com.example.beautyhair.data.model.Shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class CustomerServiceRatingActivity extends AppCompatActivity {
    TextView textShopName;
    Button btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_rating);

        String order_id = getIntent().getStringExtra("order_id");
        String shop_phone = getIntent().getStringExtra("shop_phone");

        textShopName = findViewById(R.id.textShopName);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_shop = database.getReference("Shop");

        table_shop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shop shop = snapshot.child(shop_phone).getValue(Shop.class);

                textShopName.setText(shop.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnRate = findViewById(R.id.buttonRate);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerServiceRatingActivity.this, "Đã gửi đánh giá!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}