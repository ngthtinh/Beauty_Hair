package com.example.beautyhair;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        Fragment initializedFragment = new CustomerHomeFragment();

        Bundle bundle = new Bundle();
        bundle.putString("customer_phone", getIntent().getStringExtra("customer_phone"));
        initializedFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, initializedFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId())
                {
                    case  R.id.customerActivityFragment:
                        selectedFragment = new CustomerActivityFragment();
                        break;
                    case R.id.customerHomeFragment:
                        selectedFragment = new CustomerHomeFragment();
                        break;
                    case R.id.customerAccountFragment:
                        selectedFragment = new CustomerAccountFragment();
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putString("customer_phone", getIntent().getStringExtra("customer_phone"));
                selectedFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();

                return true;
            };
}