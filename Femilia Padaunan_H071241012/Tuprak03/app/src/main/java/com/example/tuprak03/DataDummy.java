package com.example.tuprak03;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {
    private static List<Book> listBukuGlobal = new ArrayList<>();

    public static List<Book> getBukuDummy() {
        if (listBukuGlobal.isEmpty()) {
            listBukuGlobal.add(new Book("Animal Farm", "George Orwell", "Sebuah novel satir politik yang menceritakan sekelompok hewan menggulingkan manusia dari peternakan.", String.valueOf(R.drawable.buku1)));
            listBukuGlobal.add(new Book("Hujan", "Tere Liye", "Kisah tentang persahabatan, cinta, perpisahan, dan melupakan di dunia masa depan yang canggih.", String.valueOf(R.drawable.buku2)));
            listBukuGlobal.add(new Book("The Little Prince", "Antoine de Saint-Exupéry", "Kisah filosofis tentang seorang pilot yang terdampar di gurun dan bertemu dengan pangeran kecil dari asteroid lain.", String.valueOf(R.drawable.buku3)));
            listBukuGlobal.add(new Book("Madilog", "Tan Malaka", "Karya monumental yang membahas pemikiran tentang Materialisme, Dialektika, dan Logika untuk kemajuan bangsa.", String.valueOf(R.drawable.buku4)));
            listBukuGlobal.add(new Book("Dune", "Frank Herbert", "Kisah petualangan fiksi ilmiah epik tentang perebutan kekuasaan atas planet gurun Arrakis yang kaya akan rempah berharga.", String.valueOf(R.drawable.buku5)));
            listBukuGlobal.add(new Book("Bumi Manusia", "Pramoedya Ananta Toer", "Kisah cinta antara Minke dan Annelies dengan latar belakang perjuangan era kolonial Belanda.", String.valueOf(R.drawable.buku6)));
            listBukuGlobal.add(new Book("Tenggelamnya Kapal Van der Wijck", "Hamka", "Kisah cinta sejati namun tragis antara Zainuddin dan Hayati yang terhalang oleh adat Minangkabau yang kokoh.", String.valueOf(R.drawable.buku7)));
            listBukuGlobal.add(new Book("Hujan Bulan Juni", "Sapardi Djoko Damono", "Kumpulan puisi legendaris bertema ketabahan, kesabaran, dan kerinduan cinta yang disampaikan secara sederhana namun mendalam.", String.valueOf(R.drawable.buku8)));
        }
        return listBukuGlobal;
    }

    public static void tambahBukuBaru(Book buku) {
        listBukuGlobal.add(0, buku);
    }

    public static List<Book> getFavoriteBooks() {
        List<Book> favoriteList = new ArrayList<>();
        for (Book book : listBukuGlobal) {
            if (book.isFavorite()) {
                favoriteList.add(book);
            }
        }
        return favoriteList;
    }
}