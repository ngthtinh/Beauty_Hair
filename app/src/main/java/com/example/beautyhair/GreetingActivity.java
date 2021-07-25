package com.example.beautyhair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GreetingActivity extends AppCompatActivity {
    Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInAvtivity = new Intent(GreetingActivity.this, SignInActivity.class);
                startActivity(signInAvtivity);
                finish();
            }
        });
    }
}