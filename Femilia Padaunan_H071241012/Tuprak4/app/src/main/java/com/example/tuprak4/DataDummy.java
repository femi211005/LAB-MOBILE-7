package com.example.tuprak4;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {

    private static List<Book> favoriteBooks = new ArrayList<>();

    public static List<Book> getBukuDummy() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Animal Farm", "George Orwell", "1945", "Satire", "Kisah pemberontakan hewan...", R.drawable.buku1));
        list.add(new Book("Hujan", "Tere Liye", "2016", "Drama", "Kisah tentang Lail dan Esok...", R.drawable.buku2));
        list.add(new Book("The Little Prince", "Antoine de Saint-Exupéry", "1943", "Fable", "Pangeran dari planet kecil...", R.drawable.buku3));
        list.add(new Book("Madilog", "Tan Malaka", "1943", "Philosophy", "Materialisme, Dialektika, Logika...", R.drawable.buku4));
        list.add(new Book("Dune", "Frank Herbert", "1965", "Sci-Fi", "Perjuangan di planet gurun...", R.drawable.buku5));
        list.add(new Book("Bumi Manusia", "Pramoedya Ananta Toer", "1980", "History", "Kisah Minke di zaman kolonial...", R.drawable.buku6));
        list.add(new Book("Kapal Van der Wijck", "HAMKA", "1938", "Romance", "Zainuddin dan Hayati...", R.drawable.buku7));
        list.add(new Book("Hujan Bulan Juni", "Sapardi Djoko Damono", "1994", "Poetry", "Kumpulan puisi legendaris...", R.drawable.buku8));
        list.add(new Book("Murder on Orient Express", "Agatha Christie", "1934", "Mystery", "Detektif Hercule Poirot...", R.drawable.buku9));
        list.add(new Book("Teruslah Bodoh", "Tere Liye", "2024", "Satire", "Kritik sosial modern...", R.drawable.buku10));
        list.add(new Book("Tak Ada yang Tak Mungkin", "Penulis", "2024", "Genre", "Deskripsi...", R.drawable.buku11));
        list.add(new Book("Layla Majnun", "Penulis", "2024", "Genre", "Deskripsi...", R.drawable.buku12));
        list.add(new Book("Blue", "Penulis", "2024", "Genre", "Deskripsi...", R.drawable.buku13));
        list.add(new Book("No Longer Human", "Penulis", "2024", "Genre", "Deskripsi...", R.drawable.buku14));
        list.add(new Book("Putih", "Penulis", "2024", "Genre", "Deskripsi...", R.drawable.buku15));
        return list;
    }

    public static List<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    // LOGIKA BARU: Cek duplikat berdasarkan judul buku
    public static boolean addFavoriteBook(Book book) {
        for (Book b : favoriteBooks) {
            // Jika judul buku sama (mengabaikan huruf besar/kecil)
            if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
                return false; // Gagal menambah karena sudah ada
            }
        }
        favoriteBooks.add(book);
        return true; // Berhasil ditambah
    }
}