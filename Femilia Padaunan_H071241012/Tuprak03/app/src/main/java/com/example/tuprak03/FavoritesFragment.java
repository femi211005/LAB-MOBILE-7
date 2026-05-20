package com.example.tuprak03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private BookAdapter bookAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Mengarahkan ke file layout fragment_favorites.xml
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mencari RecyclerView berdasarkan ID yang sudah disamakan di XML
        rvFavorites = view.findViewById(R.id.rv_books);

        // Pengecekan keamanan: Jika rv_books berhasil ditemukan, baru pasang Adapter-nya
        if (rvFavorites != null) {
            rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
            bookAdapter = new BookAdapter();
            rvFavorites.setAdapter(bookAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Sedot hanya buku yang difavoritkan saat halaman ini terbuka
        if (bookAdapter != null) {
            bookAdapter.setData(DataDummy.getFavoriteBooks());
        }
    }
}