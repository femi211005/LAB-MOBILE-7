package com.example.tugasprak01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imgProfile;
    private TextView tvName, tvUsername, tvBio,
            tvPostCount, tvFollowerCount, tvFollowingCount;
    private Button btnEditProfile;

    private String nama     = "femi";
    private String username = "femiliapadaunan";
    private String bio      = "KMFMIPA UNHAS";
    private Uri photoUri    = null;

    private final ActivityResultLauncher<Intent> editLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            Intent data = result.getData();

                            nama     = data.getStringExtra("key_nama");
                            username = data.getStringExtra("key_username");
                            bio      = data.getStringExtra("key_bio");

                            String uriStr = data.getStringExtra("key_photo");
                            if (uriStr != null) {
                                photoUri = Uri.parse(uriStr);
                                imgProfile.setImageURI(photoUri);
                            }

                            tampilkanData();
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgProfile       = findViewById(R.id.imgProfile);
        tvName           = findViewById(R.id.tvName);
        tvUsername       = findViewById(R.id.tvUsername);
        tvBio            = findViewById(R.id.tvBio);
        tvPostCount      = findViewById(R.id.tvPostCount);
        tvFollowerCount  = findViewById(R.id.tvFollowerCount);
        tvFollowingCount = findViewById(R.id.tvFollowingCount);
        btnEditProfile   = findViewById(R.id.btnEditProfile);

        tampilkanData();

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);

            intent.putExtra("key_nama", nama);
            intent.putExtra("key_username", username);
            intent.putExtra("key_bio", bio);

            if (photoUri != null) {
                intent.putExtra("key_photo", photoUri.toString());
            }

            editLauncher.launch(intent);
        });
    }

    private void tampilkanData() {
        tvName.setText(nama);
        tvUsername.setText(username);
        tvBio.setText(bio);
        tvPostCount.setText("4");
        tvFollowerCount.setText("1.255");
        tvFollowingCount.setText("1.926");
    }
}