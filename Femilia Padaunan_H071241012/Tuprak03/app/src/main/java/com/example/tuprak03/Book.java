package com.example.tuprak03;

public class Book {
    private String title;
    private String author;
    private String description;
    private String imagePath;
    private boolean isFavorite;

    public Book(String title, String author, String description, String imagePath) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imagePath = imagePath;
        this.isFavorite = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}