package com.example.tuprak4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

public class AddBookFragment extends Fragment {

    private EditText etTitle, etAuthor, etYear, etSynopsis;
    private boolean isCoverSelected = false;

    private final ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    isCoverSelected = true;
                    Toast.makeText(getContext(), "Cover dipilih!", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        // Inisialisasi semua kolom input
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etSynopsis = view.findViewById(R.id.et_synopsis);

        Button btnPickCover = view.findViewById(R.id.btn_pick_cover);
        Button btnSaveBook = view.findViewById(R.id.btn_save_book);

        // Fungsi klik tombol Galeri
        btnPickCover.setOnClickListener(v -> galleryLauncher.launch("image/*"));

        // Fungsi klik tombol Simpan
        btnSaveBook.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            String year = etYear.getText().toString();
            String synopsis = etSynopsis.getText().toString();

            // Validasi: pastikan semua kolom terisi
            if (title.isEmpty() || author.isEmpty() || year.isEmpty() || synopsis.isEmpty()) {
                Toast.makeText(getContext(), "Tolong lengkapi semua data buku!", Toast.LENGTH_SHORT).show();
            } else if (!isCoverSelected) {
                Toast.makeText(getContext(), "Tolong pilih cover buku dulu!", Toast.LENGTH_SHORT).show();
            } else {
                // Proses penyimpanan (untuk Lab ini cukup tampilkan Toast konfirmasi)
                Toast.makeText(getContext(), "Buku '" + title + "' berhasil disimpan!", Toast.LENGTH_LONG).show();

                // Reset form setelah simpan
                etTitle.setText("");
                etAuthor.setText("");
                etYear.setText("");
                etSynopsis.setText("");
                isCoverSelected = false;
            }
        });

        return view;
    }
}