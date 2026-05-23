package com.example.tuprak5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;

public class AddBookFragment extends Fragment {

    Button btnSaveBook;
    TextInputEditText etAddTitle, etAddAuthor, etAddDesc;
    ImageView ivAddCover;
    Uri selectedImageUri = null;

    // Peluncur untuk membuka Galeri HP
    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    ivAddCover.setImageURI(selectedImageUri); // Tampilkan di layar
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        btnSaveBook = view.findViewById(R.id.btnSaveBook);
        etAddTitle = view.findViewById(R.id.etAddTitle);
        etAddAuthor = view.findViewById(R.id.etAddAuthor);
        etAddDesc = view.findViewById(R.id.etAddDesc);
        ivAddCover = view.findViewById(R.id.ivAddCover);

        // Aksi saat gambar ditekan
        ivAddCover.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        // Aksi simpan dengan Validasi
        btnSaveBook.setOnClickListener(v -> {
            String title = etAddTitle.getText().toString();
            String author = etAddAuthor.getText().toString();

            // VALIDASI KOSONG
            if (title.isEmpty() || author.isEmpty() || selectedImageUri == null) {
                Toast.makeText(getContext(), "GAGAL: Harap isi semua data dan pilih gambar sampul!", Toast.LENGTH_LONG).show();
                return; // Hentikan proses simpan
            }

            // Simpan Buku
            Book newBook = new Book(title, author, selectedImageUri.toString());
            DataDummy.addBook(newBook);

            Toast.makeText(getContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();

            // Kosongkan form setelah simpan
            etAddTitle.setText("");
            etAddAuthor.setText("");
            etAddDesc.setText("");
            ivAddCover.setImageResource(android.R.drawable.ic_menu_camera);
            selectedImageUri = null;
        });

        return view;
    }
}