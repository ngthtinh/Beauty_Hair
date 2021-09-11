package com.example.beautyhair;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShopkeeperMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_main);

        Fragment initializedFragment = new ShopkeeperHomeFragment();

        Bundle bundle = new Bundle();
        bundle.putString("shop_phone", getIntent().getStringExtra("shop_phone"));
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
                    case  R.id.shopkeeperActivityFragment:
                        selectedFragment = new ShopkeeperActivityFragment();
                        break;
                    case R.id.shopkeeperHomeFragment:
                        selectedFragment = new ShopkeeperHomeFragment();
                        break;
                    case R.id.shopkeeperAccountFragment:
                        selectedFragment = new ShopkeeperAccountFragment();
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putString("shop_phone", getIntent().getStringExtra("shop_phone"));
                selectedFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();

                return true;
            };
}