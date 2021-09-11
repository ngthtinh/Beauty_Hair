package com.example.beautyhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beautyhair.data.model.Order;
import com.example.beautyhair.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShopkeeperOrderDetailsActivity extends AppCompatActivity {
    TextView textCustomerName;
    TextView textCustomerPhone;

    ImageView imageWaiting;
    TextView textWaiting;

    ImageView imageCutting;
    TextView textCutting;

    ImageView imageDone;
    TextView textDone;

    Button btnAction;

    int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_order_details);

        state = 0;

        textCustomerName = findViewById(R.id.textCustomerName);
        textCustomerPhone = findViewById(R.id.textCustomerPhone);

        String order_id = getIntent().getStringExtra("order_id");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order = database.getReference("Order");

        table_order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Order order = snapshot.child(order_id).getValue(Order.class);
                textCustomerPhone.setText("Số điện thoại: " + order.getCustomer());

                final DatabaseReference table_user = database.getReference("User");
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.child(order.getCustomer()).getValue(User.class);
                        textCustomerName.setText(user.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageWaiting = findViewById(R.id.imageWaiting);
        textWaiting = findViewById(R.id.textWaiting);

        imageCutting = findViewById(R.id.imageCutting);
        textCutting = findViewById(R.id.textCutting);

        imageDone = findViewById(R.id.imageDone);
        textDone = findViewById(R.id.textDone);

        btnAction = findViewById(R.id.buttonAction);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (state)
                {
                    case 0:
                    {
                        textWaiting.setTextColor(getResources().getColor(R.color.purple_700));
                        imageWaiting.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_done_24));
                        btnAction.setText("Xác nhận cắt tóc");

                        break;
                    }
                    case 1:
                    {
                        textCutting.setTextColor(getResources().getColor(R.color.purple_700));
                        imageCutting.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_done_24));
                        btnAction.setText("Xác nhận hoàn tất");

                        break;
                    }
                    case 2:
                    {
                        textDone.setTextColor(getResources().getColor(R.color.purple_700));
                        imageDone.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_done_24));
                        btnAction.setText("Trở về danh sách đơn hàng");

                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final DatabaseReference table_order = database.getReference("Order");

                        table_order.child(order_id).child("status").setValue("DONE");

                        break;
                    }
                    case 3:
                    {
                        finish();
                        break;
                    }
                }
                state += 1;
            }
        });
    }
}