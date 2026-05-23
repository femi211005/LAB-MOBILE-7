package com.example.tuprak5;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());

        // Menampilkan deskripsi buku ke layar
        holder.tvDesc.setText(book.getDescription());

        // Cek pakai gambar bawaan atau gambar galeri
        if (book.getImageUriString() != null) {
            holder.ivCover.setImageURI(Uri.parse(book.getImageUriString()));
        } else {
            holder.ivCover.setImageResource(book.getImageResource());
        }

        // Atur icon bintang favorit
        if (book.isFavorite()) {
            holder.ivFavorite.setImageResource(android.R.drawable.btn_star_big_on); // Bintang nyala
        } else {
            holder.ivFavorite.setImageResource(android.R.drawable.btn_star_big_off); // Bintang mati
        }

        // Logika klik favorit beserta pesannya yang sudah memuat JUDUL BUKU
        holder.ivFavorite.setOnClickListener(v -> {
            boolean statusFavoritSekarang = book.isFavorite();
            book.setFavorite(!statusFavoritSekarang); // Balik statusnya
            notifyItemChanged(position); // Refresh tampilan item ini saja

            // Memunculkan pesan (Toast) dengan judul buku
            if (!statusFavoritSekarang) {
                Toast.makeText(v.getContext(), "Buku '" + book.getTitle() + "' ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Buku '" + book.getTitle() + "' dihapus dari Favorit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvDesc;
        ImageView ivCover, ivFavorite;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvDesc = itemView.findViewById(R.id.tvBookDesc);
            ivCover = itemView.findViewById(R.id.ivBookCover);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);
        }
    }
}