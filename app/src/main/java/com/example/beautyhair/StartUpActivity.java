package com.example.beautyhair;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        Thread startUpThread = new Thread() {
            @Override
            public void run() {
                try
                {
                    super.run();
                    sleep(2000);  //Delay of 2 seconds
                }
                catch (Exception e)
                {
                }
                finally
                {
                    Intent i = new Intent(StartUpActivity.this, SignInActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        startUpThread.start();
    }
}