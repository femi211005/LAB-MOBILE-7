package com.anugrah.libraryapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {
    private BookAdapter adapter;
    private final ArrayList<Book> favoriteBooks = new ArrayList<>();
    private ProgressBar progressBar;
    private View emptyState;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView rvFavorites = view.findViewById(R.id.rv_favorites);
        progressBar = view.findViewById(R.id.progress_bar);
        emptyState = view.findViewById(R.id.layout_empty_state);

        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BookAdapter(favoriteBooks);
        rvFavorites.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        progressBar.setVisibility(View.VISIBLE);
        emptyState.setVisibility(View.GONE);
        
        executor.execute(() -> {
            // Simulasi proses pemuatan data dari database/sumber data
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Book> filteredList = new ArrayList<>();
            for (Book book : DataSource.books) {
                if (book.isLiked()) {
                    filteredList.add(book);
                }
            }

            handler.post(() -> {
                favoriteBooks.clear();
                favoriteBooks.addAll(filteredList);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                
                progressBar.setVisibility(View.GONE);
                if (favoriteBooks.isEmpty()) {
                    emptyState.setVisibility(View.VISIBLE);
                } else {
                    emptyState.setVisibility(View.GONE);
                }
            });
        });
    }
}