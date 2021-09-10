package com.example.beautyhair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beautyhair.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {
    EditText editTextPhone, editTextPassword;
    Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editTextPhone.getText().toString()))
                Toast.makeText(SignInActivity.this, "Số điện thoại không được để trống!", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(editTextPassword.getText().toString()))
                Toast.makeText(SignInActivity.this, "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
            else {
                ProgressDialog signInDialog = new ProgressDialog(SignInActivity.this);
                signInDialog.setMessage("Đang đăng nhập...");
                signInDialog.show();

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference table_user = database.getReference("User");

                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        signInDialog.dismiss();
                        if (snapshot.child(editTextPhone.getText().toString()).exists()) {
                            User user = snapshot.child(editTextPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editTextPassword.getText().toString())) {
                                if (user.getType().equals(User.UserType.ADMIN))
                                {
                                    Toast.makeText(SignInActivity.this, "Vai trò Admin đang được xây dựng!", Toast.LENGTH_SHORT).show();
                                }

                                if (user.getType().equals(User.UserType.CUSTOMER))
                                {
                                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                                    Intent customerMainActivity = new Intent(SignInActivity.this, CustomerMainActivity.class);
                                    startActivity(customerMainActivity);
                                    finish();
                                }

                                if (user.getType().equals(User.UserType.SHOPKEEPER))
                                {
                                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                                    Intent shoopkeeperMainActivity = new Intent(SignInActivity.this, ShopkeeperMainActivity.class);
                                    startActivity(shoopkeeperMainActivity);
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Thông tin đăng nhập không chính xác!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignInActivity.this, "Tài khoản này hiện chưa được đăng ký!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }
        });
    }
}