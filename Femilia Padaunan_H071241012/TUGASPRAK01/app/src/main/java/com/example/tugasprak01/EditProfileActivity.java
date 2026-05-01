package com.example.tugasprak01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView imgProfileEdit;
    private TextView  tvGantiPhoto;
    private EditText  etNama, etUsername, etBio;
    private Button    btnSimpan, btnBatal;
    private Uri       selectedUri = null;

    private final ActivityResultLauncher<Intent> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            selectedUri = result.getData().getData();
                            imgProfileEdit.setImageURI(selectedUri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imgProfileEdit = findViewById(R.id.imgProfileEdit);
        tvGantiPhoto   = findViewById(R.id.tvGantiPhoto);
        etNama         = findViewById(R.id.etNama);
        etUsername     = findViewById(R.id.etUsername);
        etBio          = findViewById(R.id.etBio);
        btnSimpan      = findViewById(R.id.btnSimpan);
        btnBatal       = findViewById(R.id.btnBatal);

        // Ambil data dari MainActivity
        etNama.setText(getIntent().getStringExtra("key_nama"));
        etUsername.setText(getIntent().getStringExtra("key_username"));
        etBio.setText(getIntent().getStringExtra("key_bio"));

        String photoStr = getIntent().getStringExtra("key_photo");
        if (photoStr != null) {
            selectedUri = Uri.parse(photoStr);
            imgProfileEdit.setImageURI(selectedUri);
        }

        imgProfileEdit.setOnClickListener(v -> bukaGaleri());
        tvGantiPhoto.setOnClickListener(v -> bukaGaleri());

        btnSimpan.setOnClickListener(v -> {

            Intent hasil = new Intent();

            hasil.putExtra("key_nama", etNama.getText().toString());
            hasil.putExtra("key_username", etUsername.getText().toString());
            hasil.putExtra("key_bio", etBio.getText().toString());

            if (selectedUri != null) {
                hasil.putExtra("key_photo", selectedUri.toString());
            }

            setResult(RESULT_OK, hasil);

            Toast.makeText(this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();

            finish();
        });

        btnBatal.setOnClickListener(v -> finish());
    }

    private void bukaGaleri() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        galleryLauncher.launch(Intent.createChooser(intent, "Pilih Foto Profil"));
    }
}