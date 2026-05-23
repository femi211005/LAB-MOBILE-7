package com.example.tuprak5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        rvFavorites = view.findViewById(R.id.rvFavorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        // Saring (filter) hanya buku yang isFavorite == true
        List<Book> allBooks = DataDummy.getBooks();
        List<Book> favoriteBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.isFavorite()) {
                favoriteBooks.add(book);
            }
        }

        adapter = new BookAdapter(favoriteBooks);
        rvFavorites.setAdapter(adapter);

        return view;
    }
}