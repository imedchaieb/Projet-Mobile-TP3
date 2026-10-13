package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    public static final String KEY_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etFullName = findViewById(R.id.etFullName);
        EditText etRegEmail = findViewById(R.id.etRegEmail);
        EditText etRegPassword = findViewById(R.id.etRegPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnRegisterSubmit = findViewById(R.id.btnRegisterSubmit);
        TextView tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnRegisterSubmit.setOnClickListener(v -> {
            String name = etFullName.getText().toString();
            String email = etRegEmail.getText().toString();
            String password = etRegPassword.getText().toString();
            String confirm = etConfirmPassword.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Account created for: " + name, Toast.LENGTH_SHORT).show();

            // Send the name back to MainActivity
            Intent data = new Intent();
            data.putExtra(KEY_NAME, name);
            setResult(RESULT_OK, data);
            finish();
        });

        tvGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}