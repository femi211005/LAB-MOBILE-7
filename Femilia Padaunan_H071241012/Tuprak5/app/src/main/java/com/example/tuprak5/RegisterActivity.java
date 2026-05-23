package com.example.tuprak5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText etRegNim, etRegPassword;
    Button btnRegister;
    TextView tvGoToLogin;
    SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prefManager = new SharedPrefManager(this);

        etRegNim = findViewById(R.id.etRegNim);
        etRegPassword = findViewById(R.id.etRegPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        tvGoToLogin.setOnClickListener(v -> {
            finish(); // Kembali ke halaman login
        });

        btnRegister.setOnClickListener(v -> {
            String nim = etRegNim.getText().toString();
            String password = etRegPassword.getText().toString();

            if (!nim.isEmpty() && !password.isEmpty()) {
                prefManager.saveUser(nim, password); // Ini akan menimpa akun lama
                Toast.makeText(this, "Registrasi Berhasil! Silakan Login.", Toast.LENGTH_LONG).show();
                finish(); // Kembali ke halaman login setelah sukses
            } else {
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}