package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    public static final String KEY_OPTIONS_COST = "options_cost";
    public static final String KEY_OPTIONS_TEXT = "options_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        CheckBox cbGps = findViewById(R.id.cbGps);
        CheckBox cbBabySeat = findViewById(R.id.cbBabySeat);
        CheckBox cbInsurance = findViewById(R.id.cbInsurance);
        Button btnValidate = findViewById(R.id.btnValidate);

        btnValidate.setOnClickListener(v -> {
            double cost = 0;
            String text = "";

            if (cbGps.isChecked()) {
                cost += 10;
                text += "GPS ";
            }
            if (cbBabySeat.isChecked()) {
                cost += 5;
                text += "Baby seat ";
            }
            if (cbInsurance.isChecked()) {
                cost += 20;
                text += "Insurance ";
            }

            if (text.isEmpty()) {
                text = "No options selected";
            }

            // Send the result back
            Intent data = new Intent();
            data.putExtra(KEY_OPTIONS_COST, cost);
            data.putExtra(KEY_OPTIONS_TEXT, text);
            setResult(RESULT_OK, data);//setResult() is used to send a result back from one Activity to another Activity
            finish();
        });
    }
}