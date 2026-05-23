package com.example.tuprak4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private RecyclerView rvBooks;
    private ProgressBar progressBar;
    private List<Book> listAsli = DataDummy.getBukuDummy();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvBooks = view.findViewById(R.id.rv_books);
        progressBar = view.findViewById(R.id.progress_bar);
        SearchView searchView = view.findViewById(R.id.search_view);

        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBooks.setAdapter(new BookAdapter(listAsli));

        // Listener untuk pencarian
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                jalankanPencarian(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                jalankanPencarian(newText);
                return true;
            }
        });

        return view;
    }

    private void jalankanPencarian(String query) {
        progressBar.setVisibility(View.VISIBLE);
        rvBooks.setVisibility(View.GONE);

        Executors.newSingleThreadExecutor().execute(() -> {
            // Simulasi proses pencarian di background
            try { Thread.sleep(500); } catch (Exception e) {}

            List<Book> filteredList = new ArrayList<>();
            for (Book buku : listAsli) {
                if (buku.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(buku);
                }
            }

            new Handler(Looper.getMainLooper()).post(() -> {
                progressBar.setVisibility(View.GONE);
                rvBooks.setVisibility(View.VISIBLE);
                rvBooks.setAdapter(new BookAdapter(filteredList));
            });
        });
    }
}