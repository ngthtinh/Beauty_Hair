package com.example.beautyhair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beautyhair.data.model.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {
    EditText editTextPhone, editTextPassword;
    Button buttonSignIn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        textView = findViewById(R.id.textView2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_account = database.getReference("Accounts");

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // ProgressDialog signInDialog = new ProgressDialog(SignInActivity.this);
                // signInDialog.setMessage("Please wait...");
                // signInDialog.show();

                table_account.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        // signInDialog.dismiss();
                        textView.setText(editTextPhone.getText().toString());
                        if (snapshot.child(editTextPhone.getText().toString()).exists()) {
                            Account account = snapshot.child(editTextPhone.getText().toString()).getValue(Account.class);
                            if (account.getPassword().equals(editTextPassword.getText().toString())) {
                                Toast.makeText(SignInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(mainActivity);
                            } else {
                                Toast.makeText(SignInActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignInActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
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