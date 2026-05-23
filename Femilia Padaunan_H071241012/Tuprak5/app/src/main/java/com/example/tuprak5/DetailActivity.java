package com.example.tuprak5;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView ivCover;
    TextView tvTitle, tvAuthor, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Memunculkan tombol panah kembali (Back) di Action Bar atas
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Buku");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ivCover = findViewById(R.id.ivDetailCover);
        tvTitle = findViewById(R.id.tvDetailTitle);
        tvAuthor = findViewById(R.id.tvDetailAuthor);
        tvDesc = findViewById(R.id.tvDetailDesc);

        // Menangkap data yang dikirim dari BookAdapter
        String title = getIntent().getStringExtra("TITLE");
        String author = getIntent().getStringExtra("AUTHOR");
        String desc = getIntent().getStringExtra("DESC");
        int imageRes = getIntent().getIntExtra("IMAGE_RES", 0);
        String imageUri = getIntent().getStringExtra("IMAGE_URI");

        // Menampilkan data ke layar
        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvDesc.setText(desc);

        // Menentukan pakai gambar bawaan atau gambar dari galeri
        if (imageUri != null) {
            ivCover.setImageURI(Uri.parse(imageUri));
        } else {
            ivCover.setImageResource(imageRes);
        }
    }

    // Fungsi agar saat panah kembali di sudut kiri atas ditekan, halaman tertutup dan kembali ke Home
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}