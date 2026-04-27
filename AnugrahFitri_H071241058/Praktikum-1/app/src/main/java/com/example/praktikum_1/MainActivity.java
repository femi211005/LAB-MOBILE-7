package com.example.praktikum_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private TextView tvFullName, tvBio, tvUsernameTop;
    private ShapeableImageView imgProfile;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();

                    tvFullName.setText(data.getStringExtra("NAME"));
                    tvUsernameTop.setText(data.getStringExtra("USERNAME"));
                    tvBio.setText(data.getStringExtra("BIO"));

                    String newImageUriString = data.getStringExtra("NEW_IMAGE");
                    if (newImageUriString != null && !newImageUriString.isEmpty()) {
                        Uri uri = Uri.parse(newImageUriString);
                        imgProfile.setImageURI(uri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFullName = findViewById(R.id.tvFullName);
        tvBio = findViewById(R.id.tvBio);
        tvUsernameTop = findViewById(R.id.tvUsernameTop);
        imgProfile = findViewById(R.id.imgProfile);
        Button btnEdit = findViewById(R.id.btnEditProfile);

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            intent.putExtra("OLD_NAME", tvFullName.getText().toString());
            intent.putExtra("OLD_USERNAME", tvUsernameTop.getText().toString());
            intent.putExtra("OLD_BIO", tvBio.getText().toString());
            editProfileLauncher.launch(intent);
        });

        setupBottomNav();

        setupHighlights();
    }

    private void setupBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        if (bottomNav != null) {
            BadgeDrawable threadsBadge = bottomNav.getOrCreateBadge(R.id.nav_threads);
            threadsBadge.setVisible(true);
            threadsBadge.setNumber(1);
            threadsBadge.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));

            BadgeDrawable heartBadge = bottomNav.getOrCreateBadge(R.id.nav_heart);
            heartBadge.setVisible(true);
            heartBadge.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }
    }

    private void setupHighlights() {
        View hl1 = findViewById(R.id.highlight1);
        if (hl1 != null) {
            ((ImageView) hl1.findViewById(R.id.imgHighlight)).setImageResource(R.drawable.anime);
            ((TextView) hl1.findViewById(R.id.tvHighlightName)).setText("anime");
        }

        View hl2 = findViewById(R.id.highlight2);
        if (hl2 != null) {
            ((ImageView) hl2.findViewById(R.id.imgHighlight)).setImageResource(R.drawable.coding);
            ((TextView) hl2.findViewById(R.id.tvHighlightName)).setText("coding");
        }

        View hl3 = findViewById(R.id.highlight3);
        if (hl3 != null) {
            ((ImageView) hl3.findViewById(R.id.imgHighlight)).setImageResource(R.drawable.game);
            ((TextView) hl3.findViewById(R.id.tvHighlightName)).setText("game");
        }
    }
}