package com.example.tuprak4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> bookList;
    private boolean isHomeFragment = true; // Saklar penanda, default-nya true (berada di Home)

    // Constructor 1: Digunakan oleh HomeFragment
    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    // Constructor 2: Digunakan oleh FavoritesFragment
    public BookAdapter(List<Book> bookList, boolean isHomeFragment) {
        this.bookList = bookList;
        this.isHomeFragment = isHomeFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor() + " (" + book.getYear() + ")");
        holder.tvDesc.setText(book.getDescription());
        holder.imgBook.setImageResource(book.getImageRes());

        // --- CEK SAKLAR UNTUK KLIK SEKALI ---
        if (isHomeFragment) {
            // Jika di halaman Home, aktifkan fitur tambah ke favorit
            holder.itemView.setOnClickListener(v -> {
                boolean isAdded = DataDummy.addFavoriteBook(book);
                if (isAdded) {
                    Toast.makeText(v.getContext(), book.getTitle() + " ditambahkan ke Favorite!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Buku \"" + book.getTitle() + "\" sudah ada di Favourites!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Jika di halaman Favorites, matikan fitur klik (tidak ada aksi)
            holder.itemView.setOnClickListener(null);
        }

        // --- AKSI TEKAN LAMA (Tetap diaktifkan agar kamu bisa baca sinopsis di mana saja) ---
        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(book.getTitle());
            builder.setMessage("Penulis: " + book.getAuthor() + "\n" +
                    "Tahun Terbit: " + book.getYear() + "\n\n" +
                    "Sinopsis / Isi Buku:\n" + book.getDescription());
            builder.setPositiveButton("Tutup", (dialog, which) -> dialog.dismiss());
            builder.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return bookList != null ? bookList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView tvTitle, tvAuthor, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}