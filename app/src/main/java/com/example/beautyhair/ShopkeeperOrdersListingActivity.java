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

import com.example.beautyhair.adapter.OrderAdapter;
import com.example.beautyhair.data.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopkeeperOrdersListingActivity extends AppCompatActivity {
    private RecyclerView recyclerOrdersView;

    private OrderAdapter orderAdapter;
    private List<Order> mOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_orders_listing);

        recyclerOrdersView = findViewById(R.id.recyclerOrdersView);
        recyclerOrdersView.setHasFixedSize(true);
        recyclerOrdersView.setLayoutManager(new LinearLayoutManager(this));

        mOrders = new ArrayList<>();
        readOrders();
    }

    private void readOrders(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order = database.getReference("Order");

        String shop_phone = getIntent().getStringExtra("shop_phone");

        table_order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mOrders.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);
                    if (order.getShopkeeper().equals(shop_phone) && (order.getStatus() == Order.StatusType.PROCESSING))
                        mOrders.add(order);
                }

                orderAdapter = new OrderAdapter(ShopkeeperOrdersListingActivity.this, mOrders);
                recyclerOrdersView.setAdapter(orderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}