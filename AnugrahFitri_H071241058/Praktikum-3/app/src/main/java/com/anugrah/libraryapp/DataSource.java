package com.anugrah.libraryapp;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Book> books = new ArrayList<>();

    static {
        books.add(new Book("Laskar Pelangi", "Andrea Hirata", "2005", "Kisah perjuangan 10 anak Belitung.", R.drawable.cover1, "Fiction", 4.8f));
        books.add(new Book("Bumi", "Tere Liye", "2014", "Petualangan di dunia paralel.", R.drawable.cover2, "Fantasy", 4.5f));
        books.add(new Book("Filosofi Kopi", "Dee Lestari", "2006", "Kumpulan cerita pendek.", R.drawable.cover3, "Literature", 4.3f));
        books.add(new Book("Ronggeng Dukuh Paruk", "Ahmad Tohari", "1982", "Tragedi kemanusiaan di desa terpencil.", R.drawable.cover4, "Classic", 4.7f));
        books.add(new Book("Pulang", "Leila S. Chudori", "2012", "Drama keluarga dan sejarah.", R.drawable.cover5, "Historical", 4.6f));
        books.add(new Book("Perahu Kertas", "Dee Lestari", "2009", "Kisah cinta dan impian.", R.drawable.cover6, "Romance", 4.2f));
        books.add(new Book("Negeri 5 Menara", "A. Fuadi", "2009", "Kehidupan di pesantren.", R.drawable.cover7, "Education", 4.4f));
        books.add(new Book("Hujan", "Tere Liye", "2016", "Persahabatan dan perpisahan.", R.drawable.cover8, "Sci-Fi", 4.5f));
        books.add(new Book("Cantik itu Luka", "Eka Kurniawan", "2002", "Seni bercerita yang luar biasa.", R.drawable.cover9, "Fiction", 4.9f));
        books.add(new Book("Garis Waktu", "Fiersa Besari", "2016", "Kumpulan tulisan puitis.", R.drawable.cover10, "Poetry", 4.1f));
        books.add(new Book("Sang Pemimpi", "Andrea Hirata", "2006", "Lanjutan Laskar Pelangi.", R.drawable.cover11, "Fiction", 4.7f));
        books.add(new Book("Anak Semua Bangsa", "Pramoedya Ananta Toer", "1980", "Karya besar sastra Indonesia.", R.drawable.cover12, "Classic", 4.9f));
        books.add(new Book("Dilan 1990", "Pidi Baiq", "2014", "Kisah cinta SMA tahun 90-an.", R.drawable.cover13, "Romance", 4.0f));
        books.add(new Book("Supernova", "Dee Lestari", "2001", "Fiksi ilmiah dan spiritualitas.", R.drawable.cover14, "Sci-Fi", 4.4f));
        books.add(new Book("Tenggelamnya Kapal Van der Wijck", "Hamka", "1938", "Kisah cinta tragis berlatar adat.", R.drawable.cover15, "Classic", 4.8f));
    }
}