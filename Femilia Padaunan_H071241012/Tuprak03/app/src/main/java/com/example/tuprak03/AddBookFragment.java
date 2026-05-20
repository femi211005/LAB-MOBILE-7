package com.example.tuprak03;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddBookFragment extends Fragment {

    private ImageView ivCoverBuku;
    private Button btnPilihCover, btnSimpan;
    private EditText etJudul, etPenulis,  etSinopsis;
    private Uri selectedImageUri = null;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    ivCoverBuku.setImageURI(selectedImageUri);
                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivCoverBuku = view.findViewById(R.id.ivCoverBuku);
        btnPilihCover = view.findViewById(R.id.btnPilihCover);
        btnSimpan = view.findViewById(R.id.btnSimpan);
        etJudul = view.findViewById(R.id.etJudul);
        etPenulis = view.findViewById(R.id.etPenulis);
        etSinopsis = view.findViewById(R.id.etSinopsis);

        btnPilihCover.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        });

        btnSimpan.setOnClickListener(v -> {
            String judul = etJudul.getText().toString().trim();
            String penulis = etPenulis.getText().toString().trim();
            String sinopsis = etSinopsis.getText().toString().trim();

            if (judul.isEmpty() || penulis.isEmpty() || sinopsis.isEmpty()) {
                Toast.makeText(getContext(), "Tolong lengkapi data judul, penulis, dan sinopsis!", Toast.LENGTH_SHORT).show();
                return;
            }

            String imagePath;
            if (selectedImageUri != null) {
                imagePath = selectedImageUri.toString();
            } else {
                imagePath = String.valueOf(R.drawable.buku1);
            }

            Book bukuBaru = new Book(judul, penulis, sinopsis, imagePath);
            DataDummy.tambahBukuBaru(bukuBaru);

            Toast.makeText(getContext(), "Buku '" + judul + "' berhasil ditambahkan!", Toast.LENGTH_SHORT).show();

            etJudul.setText("");
            etPenulis.setText("");
            etSinopsis.setText("");
            ivCoverBuku.setImageResource(0);
            selectedImageUri = null;

            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();

                BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
                if (bottomNav != null) {
                    bottomNav.setSelectedItemId(R.id.nav_home);
                }
            }
        });
    }
}