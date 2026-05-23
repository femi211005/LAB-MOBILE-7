package com.example.tuprak4;

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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private ProgressBar progressBar;
    private BookAdapter bookAdapter;

    private ExecutorService executorService;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorites = view.findViewById(R.id.rv_favorites);
        progressBar = view.findViewById(R.id.progressBar);

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Langsung panggil proses muat data
        loadFavoritesData();
    }

    private void loadFavoritesData() {
        progressBar.setVisibility(View.VISIBLE);
        rvFavorites.setVisibility(View.GONE);

        executorService.execute(() -> {
            try {
                // Animasi loading 1,5 detik
                Thread.sleep(1500);

                // Mengambil data favorit
                List<Book> favoriteBooks = DataDummy.getFavoriteBooks();

                handler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    rvFavorites.setVisibility(View.VISIBLE);

                    // SOLUSINYA DI SINI:
                    // Kita langsung memasukkan favoriteBooks ke dalam kurung BookAdapter
                    // dan mengatur adapternya setelah data selesai dimuat
                    // Tambahkan kata "false" untuk mematikan saklar klik di adapter
                    bookAdapter = new BookAdapter(favoriteBooks, false);
                    rvFavorites.setAdapter(bookAdapter);
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}