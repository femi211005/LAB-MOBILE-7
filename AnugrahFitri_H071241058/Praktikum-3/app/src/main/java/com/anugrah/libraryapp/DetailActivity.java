package com.anugrah.libraryapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgCover = findViewById(R.id.img_detail_cover);
        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvAuthor = findViewById(R.id.tv_detail_author);
        TextView tvYear = findViewById(R.id.tv_detail_year);
        TextView tvGenre = findViewById(R.id.tv_detail_genre);
        TextView tvBlurb = findViewById(R.id.tv_detail_blurb);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        Button btnLike = findViewById(R.id.btn_like);

        int bookIndex = getIntent().getIntExtra("EXTRA_BOOK_INDEX", -1);
        if (bookIndex != -1) {
            currentBook = DataSource.books.get(bookIndex);

            tvTitle.setText(currentBook.getJudul());
            tvAuthor.setText(currentBook.getPenulis());
            tvYear.setText("Tahun Terbit: " + currentBook.getTahunTerbit());
            tvGenre.setText(currentBook.getGenre());
            tvBlurb.setText(currentBook.getBlurb());
            ratingBar.setRating(currentBook.getRating());

            if (currentBook.getGambarUri() != null) {
                imgCover.setImageURI(currentBook.getGambarUri());
            } else {
                imgCover.setImageResource(currentBook.getGambarRes());
            }

            updateLikeButton(btnLike);

            btnLike.setOnClickListener(v -> {
                currentBook.setLiked(!currentBook.isLiked());
                updateLikeButton(btnLike);
                Toast.makeText(this, currentBook.isLiked() ? "Ditambahkan ke Favorit" : "Dihapus dari Favorit", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void updateLikeButton(Button btn) {
        if (currentBook.isLiked()) {
            btn.setText("Unlike");
            btn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            btn.setText("Like");
            btn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
    }
}