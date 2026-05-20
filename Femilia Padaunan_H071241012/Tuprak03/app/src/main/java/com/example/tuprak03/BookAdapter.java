package com.example.tuprak03;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList = new ArrayList<>();

    public void setData(List<Book> books) {
        this.bookList = books;
        notifyDataSetChanged();
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

        holder.tvBookTitle.setText(book.getTitle());
        holder.tvBookAuthor.setText(book.getAuthor());
        holder.tvBookDescription.setText(book.getDescription());

        String imgPath = book.getImagePath();
        try {
            int imageResource = Integer.parseInt(imgPath);
            holder.imgBook.setImageResource(imageResource);
        } catch (NumberFormatException e) {
            Uri uri = Uri.parse(imgPath);
            holder.imgBook.setImageURI(uri);
        }

        if (book.isFavorite()) {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }

        holder.btnFavorite.setOnClickListener(v -> {
            boolean isNowFavorite = !book.isFavorite();
            book.setFavorite(isNowFavorite);

            if (isNowFavorite) {
                Toast.makeText(v.getContext(), "Buku '" + book.getTitle() + "' ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Buku '" + book.getTitle() + "' dihapus dari favorit", Toast.LENGTH_SHORT).show();
            }

            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook, btnFavorite;
        TextView tvBookTitle, tvBookAuthor, tvBookDescription;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.imgBook);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvBookDescription = itemView.findViewById(R.id.tvBookDescription);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }
}