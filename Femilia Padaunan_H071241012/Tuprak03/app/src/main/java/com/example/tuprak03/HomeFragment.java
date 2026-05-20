package com.example.tuprak03;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private ProgressBar progressBar;
    private SearchView searchView;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBooks = view.findViewById(R.id.rv_books);
        progressBar = view.findViewById(R.id.progressBar);
        searchView = view.findViewById(R.id.searchView);

        rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        bookAdapter = new BookAdapter();
        rvBooks.setAdapter(bookAdapter);

        // Pencarian (Search) tetap di onViewCreated
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    // KUNCI UTAMA PERBAIKAN: Fungsi ini berjalan setiap kali fragment tampil/terbuka kembali
    @Override
    public void onResume() {
        super.onResume();
        if (bookAdapter != null) {
            // Paksa adapter untuk mengambil data paling baru dari DataDummy
            bookAdapter.setData(DataDummy.getBukuDummy());
        }
    }

    private void performSearch(String query) {
        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Book> filteredList = new ArrayList<>();
            for (Book book : DataDummy.getBukuDummy()) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(book);
                }
            }

            handler.post(() -> {
                if (progressBar != null) progressBar.setVisibility(View.GONE);
                if (rvBooks != null) rvBooks.setVisibility(View.VISIBLE);
                bookAdapter.setData(filteredList);
            });
        });
    }
}