package com.anugrah.libraryapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class AddBookFragment extends Fragment {
    private ImageView imgPreview;
    private Uri selectedImageUri;
    private EditText etTitle, etAuthor, etYear, etGenre, etBlurb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        imgPreview = view.findViewById(R.id.img_add_cover);
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etGenre = view.findViewById(R.id.et_genre);
        etBlurb = view.findViewById(R.id.et_blurb);
        
        // Perbaikan: Gunakan MaterialButton sesuai layout XML terbaru
        MaterialButton btnPick = view.findViewById(R.id.btn_pick_image);
        MaterialButton btnSave = view.findViewById(R.id.btn_save);

        ActivityResultLauncher<String> pickImage = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedImageUri = uri;
                        imgPreview.setImageURI(uri);
                    }
                }
        );

        if (btnPick != null) {
            btnPick.setOnClickListener(v -> pickImage.launch("image/*"));
        }

        if (btnSave != null) {
            btnSave.setOnClickListener(v -> {
                String title = etTitle.getText().toString();
                String author = etAuthor.getText().toString();
                String year = etYear.getText().toString();
                String genre = etGenre.getText().toString();
                String blurb = etBlurb.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(getContext(), "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedImageUri == null) {
                    Toast.makeText(getContext(), "Pilih gambar sampul terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                DataSource.books.add(new Book(title, author, year, blurb, selectedImageUri, genre));
                Toast.makeText(getContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                
                // Kosongkan form setelah simpan
                etTitle.setText("");
                etAuthor.setText("");
                etYear.setText("");
                etGenre.setText("");
                etBlurb.setText("");
                imgPreview.setImageResource(android.R.drawable.ic_menu_gallery);
                selectedImageUri = null;
            });
        }

        return view;
    }
}