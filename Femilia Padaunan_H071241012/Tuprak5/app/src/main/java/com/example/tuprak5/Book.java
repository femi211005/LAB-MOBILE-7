package com.example.tuprak5;

public class Book {
    private String title;
    private String author;
    private String description;
    private String genre; // <-- Tambahan Kategori Genre
    private int imageResource;
    private String imageUriString;
    private boolean isFavorite;

    // Constructor untuk 10 buku awal
    public Book(String title, String author, String description, String genre, int imageResource) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre; // <-- Tambahan
        this.imageResource = imageResource;
        this.isFavorite = false;
    }

    // Constructor untuk buku baru
    public Book(String title, String author, String imageUriString) {
        this.title = title;
        this.author = author;
        this.description = "Deskripsi belum tersedia untuk buku ini.";
        this.genre = "Semua Genre"; // Default untuk buku yang baru ditambah
        this.imageUriString = imageUriString;
        this.imageResource = 0;
        this.isFavorite = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getGenre() { return genre; } // <-- Getter untuk Genre
    public int getImageResource() { return imageResource; }
    public String getImageUriString() { return imageUriString; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}