package com.anugrah.libraryapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> bookList;
    private List<Book> bookListFull;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
        this.bookListFull = new ArrayList<>(bookList);
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
        holder.tvTitle.setText(book.getJudul());
        holder.tvAuthor.setText(book.getPenulis());
        holder.tvGenre.setText(book.getGenre());

        if (book.getGambarUri() != null) {
            holder.imgCover.setImageURI(book.getGambarUri());
        } else {
            holder.imgCover.setImageResource(book.getGambarRes());
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("EXTRA_BOOK_INDEX", DataSource.books.indexOf(book));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void filter(String text) {
        bookList.clear();
        if (text.isEmpty()) {
            bookList.addAll(bookListFull);
        } else {
            text = text.toLowerCase();
            for (Book item : bookListFull) {
                if (item.getJudul().toLowerCase().contains(text)) {
                    bookList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView tvTitle, tvAuthor, tvGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.img_book_cover);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }
    }
}