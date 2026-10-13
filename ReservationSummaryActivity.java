package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationSummaryActivity extends AppCompatActivity {

    private static final int REQUEST_OPTIONS = 1;
    private static final int REQUEST_CONFIRM = 2;

    private String carName = "";
    private double pricePerDay = 0.0;
    private double optionsPerDay = 0.0;
    private String optionsText = "No options selected";
    private int days = 0;
    private double total = 0.0;

    private TextView tvOptionsSelected;
    private Button btnConfirmReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        TextView tvSummaryCarName = findViewById(R.id.tvSummaryCarName);
        TextView tvSummaryPricePerDay = findViewById(R.id.tvSummaryPricePerDay);
        EditText etNumberOfDays = findViewById(R.id.etNumberOfDays);
        Button btnCalculateTotal = findViewById(R.id.btnCalculateTotal);
        TextView tvTotalResult = findViewById(R.id.tvTotalResult);
        Button btnOptions = findViewById(R.id.btnOptions);
        tvOptionsSelected = findViewById(R.id.tvOptionsSelected);
        btnConfirmReservation = findViewById(R.id.btnConfirmReservation);

        Bundle reservationBundle = getIntent().getBundleExtra(CarDetailActivity.EXTRA_RESERVATION_BUNDLE);

        if (reservationBundle != null) {
            carName = reservationBundle.getString(CarDetailActivity.KEY_RES_CAR_NAME, "Unknown");
            pricePerDay = reservationBundle.getDouble(CarDetailActivity.KEY_RES_CAR_PRICE, 0.0);

            tvSummaryCarName.setText(carName);
            tvSummaryPricePerDay.setText(pricePerDay + " TND/day");
        }

        btnOptions.setOnClickListener(v -> {
            Intent intent = new Intent(ReservationSummaryActivity.this, OptionsActivity.class);
            startActivityForResult(intent, REQUEST_OPTIONS);//req opt identifies which activity is requested when it's return the code pass to onActivityResult()
        });

        btnCalculateTotal.setOnClickListener(v -> {
            String daysText = etNumberOfDays.getText().toString();

            if (daysText.isEmpty()) {
                Toast.makeText(this, "Please enter a number of days", Toast.LENGTH_SHORT).show();
                return;
            }

            days = Integer.parseInt(daysText);
            total = (pricePerDay + optionsPerDay) * days;
            tvTotalResult.setText("Total: " + total + " TND");
        });


        btnConfirmReservation.setOnClickListener(v -> {
            if (total == 0) {
                Toast.makeText(this, "Please calculate the total first", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(ReservationSummaryActivity.this, ConfirmActivity.class);
            intent.putExtra(ConfirmActivity.KEY_CAR_NAME, carName);
            intent.putExtra(ConfirmActivity.KEY_DAYS, days);
            intent.putExtra(ConfirmActivity.KEY_OPTIONS, optionsText);
            intent.putExtra(ConfirmActivity.KEY_TOTAL, total);
            startActivityForResult(intent, REQUEST_CONFIRM);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result from OptionsActivity
        if (requestCode == REQUEST_OPTIONS && resultCode == RESULT_OK && data != null) {
            optionsPerDay = data.getDoubleExtra(OptionsActivity.KEY_OPTIONS_COST, 0.0);
            optionsText = data.getStringExtra(OptionsActivity.KEY_OPTIONS_TEXT);
            tvOptionsSelected.setText(optionsText + " (+" + optionsPerDay + " TND/day)");
        }


        if (requestCode == REQUEST_CONFIRM) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Reservation confirmed", Toast.LENGTH_LONG).show();
                btnConfirmReservation.setEnabled(false);
                btnConfirmReservation.setText("Reservation confirmed");
            } else {
                Toast.makeText(this, "Reservation cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}