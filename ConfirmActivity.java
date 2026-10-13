package com.example.carrentalapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {

    public static final String KEY_CAR_NAME = "confirm_car_name";
    public static final String KEY_DAYS = "confirm_days";
    public static final String KEY_OPTIONS = "confirm_options";
    public static final String KEY_TOTAL = "confirm_total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        TextView tvConfirmCar = findViewById(R.id.tvConfirmCar);
        TextView tvConfirmDays = findViewById(R.id.tvConfirmDays);
        TextView tvConfirmOptions = findViewById(R.id.tvConfirmOptions);
        TextView tvConfirmTotal = findViewById(R.id.tvConfirmTotal);
        Button btnConfirmYes = findViewById(R.id.btnConfirmYes);
        Button btnConfirmNo = findViewById(R.id.btnConfirmNo);

        // 1) Receive the data sent by ReservationSummaryActivity
        String carName = getIntent().getStringExtra(KEY_CAR_NAME);
        int days = getIntent().getIntExtra(KEY_DAYS, 0);
        String options = getIntent().getStringExtra(KEY_OPTIONS);
        double total = getIntent().getDoubleExtra(KEY_TOTAL, 0.0);

        tvConfirmCar.setText("Car: " + carName);
        tvConfirmDays.setText("Days: " + days);
        tvConfirmOptions.setText("Options: " + options);
        tvConfirmTotal.setText("Total: " + total + " TND");

        // 2) Confirm -> RESULT_OK
        btnConfirmYes.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        // 3) Cancel -> RESULT_CANCELED
        btnConfirmNo.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}