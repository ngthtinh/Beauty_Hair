package com.example.beautyhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyhair.data.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class CustomerBookingSuccessfulActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking_successful);

        String order_id = getIntent().getStringExtra("order_id");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order = database.getReference("Order");

        table_order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Order order = snapshot.child(order_id).getValue(Order.class);
                if (order.getStatus() == Order.StatusType.DONE)
                {
                    Intent customerServiceRating = new Intent(CustomerBookingSuccessfulActivity.this, CustomerServiceRatingActivity.class);
                    customerServiceRating.putExtra("order_id", order_id);
                    customerServiceRating.putExtra("shop_phone", order.getShopkeeper());
                    startActivity(customerServiceRating);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}