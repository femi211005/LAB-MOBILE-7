package com.example.tuprak5;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {
    private static List<Book> bookList;

    public static List<Book> getBooks() {
        if (bookList == null) {
            bookList = new ArrayList<>();
            // Perhatikan ada tambahan genre ("Fiksi", dll) di parameter ke-4
            bookList.add(new Book("Animal Farm", "George Orwell", "Novel satir politik yang mengisahkan pemberontakan hewan di sebuah peternakan.", "Fiksi", R.drawable.buku1));
            bookList.add(new Book("Hujan", "Tere Liye", "Kisah tentang persahabatan, cinta, dan melupakan di tengah bencana alam fiksi ilmiah.", "Fiksi", R.drawable.buku2));
            bookList.add(new Book("The Little Prince", "Antoine de Saint-Exupéry", "Perjalanan seorang pangeran kecil dari planet B612 yang penuh makna kehidupan.", "Fiksi", R.drawable.buku3));
            bookList.add(new Book("Madilog", "Tan Malaka", "Pemikiran materialisme, dialektika, dan logika yang berkontribusi terhadap gagasan kebangsaan.", "Non-Fiksi", R.drawable.buku4));
            bookList.add(new Book("Dune", "Frank Herbert", "Kisah fiksi ilmiah epik tentang perebutan kekuasaan dan rempah di planet gurun Arrakis.", "Fiksi", R.drawable.buku5));
            bookList.add(new Book("Bumi Manusia", "Pramoedya Ananta Toer", "Kisah Minke dan Annelies berlatar masa kolonial Hindia Belanda yang penuh pergolakan.", "Sejarah", R.drawable.buku6));
            bookList.add(new Book("Tenggelamnya Kapal Van der Wijck", "HAMKA", "Kisah cinta tragis Zainuddin dan Hayati yang terhalang oleh adat istiadat Minangkabau.", "Sejarah", R.drawable.buku7));
            bookList.add(new Book("Hujan Bulan Juni", "Sapardi Djoko Damono", "Kumpulan puisi romantis nan syahdu yang diadaptasi menjadi sebuah novel.", "Fiksi", R.drawable.buku8));
            bookList.add(new Book("Murder on the Orient Express", "Agatha Christie", "Misteri pembunuhan menegangkan di atas kereta ekspres yang dipecahkan detektif.", "Fiksi", R.drawable.buku9));
            bookList.add(new Book("Teruslah Bodoh Jangan Pintar", "Tere Liye", "Novel sindiran tentang ironi kehidupan, sistem, dan kebodohan manusia modern.", "Non-Fiksi", R.drawable.buku10));
        }
        return bookList;
    }

    public static void addBook(Book book) {
        getBooks().add(0, book);
    }
}