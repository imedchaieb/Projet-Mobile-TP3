package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CAR_BUNDLE = "car_bundle";
    public static final String KEY_CAR_NAME = "car_name";
    public static final String KEY_CAR_BRAND = "car_brand";
    public static final String KEY_CAR_PRICE = "car_price";
    public static final String KEY_CAR_DESC = "car_desc";

    private static final int REQUEST_REGISTER = 3;
    private TextView tvWelcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnBrowseCars = findViewById(R.id.btnBrowseCars);
        Button btnFeaturedCar = findViewById(R.id.btnFeaturedCar);
        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Open RegisterActivity and wait for a result
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivityForResult(intent, REQUEST_REGISTER);
        });

        btnBrowseCars.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CarListActivity.class);
            startActivity(intent);
        });

        btnFeaturedCar.setOnClickListener(v -> {
            Bundle carBundle = new Bundle();
            carBundle.putString(KEY_CAR_NAME, "Model 3");
            carBundle.putString(KEY_CAR_BRAND, "Tesla");
            carBundle.putDouble(KEY_CAR_PRICE, 90.0);
            carBundle.putString(KEY_CAR_DESC, "Fully electric, autopilot included — perfect for a weekend trip.");

            Intent intent = new Intent(MainActivity.this, CarDetailActivity.class);
            intent.putExtra(EXTRA_CAR_BUNDLE, carBundle);
            startActivity(intent);
        });
    }

    // Receive the result from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra(RegisterActivity.KEY_NAME);
            tvWelcomeUser.setText("Welcome " + name);
            tvWelcomeUser.setVisibility(View.VISIBLE);
        }
    }
}