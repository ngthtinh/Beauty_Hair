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

public class SignUpActivity extends AppCompatActivity {
    EditText editTextPhone, editTextName, editTextPassword;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedUpstanceState) {
        super.onCreate(savedUpstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editTextPhone.getText().toString()))
                Toast.makeText(SignUpActivity.this, "Số điện thoại không được để trống!", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(editTextName.getText().toString()))
                Toast.makeText(SignUpActivity.this, "Tên của bạn không được để trống!", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(editTextPassword.getText().toString()))
                Toast.makeText(SignUpActivity.this, "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
            else {
                ProgressDialog signUpDialog = new ProgressDialog(SignUpActivity.this);
                signUpDialog.setMessage("Đang đăng ký...");
                signUpDialog.show();

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference table_user = database.getReference("User");

                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        signUpDialog.dismiss();

                        if (snapshot.child(editTextPhone.getText().toString()).exists()) {
                            Toast.makeText(SignUpActivity.this, "Số điện thoại này đã được đăng ký!", Toast.LENGTH_SHORT).show();
                        } else {
                            User user = new User(editTextName.getText().toString(), editTextPassword.getText().toString());
                            table_user.child(editTextPhone.getText().toString()).setValue(user);

                            Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                            Intent mainActivity = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(mainActivity);
                            finish();
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