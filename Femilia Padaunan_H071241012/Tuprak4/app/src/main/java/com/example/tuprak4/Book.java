package com.example.tuprak4;

public class Book {
    private String title, author, year, genre, description;
    private int imageRes;

    public Book(String title, String author, String year, String genre, String description, int imageRes) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.imageRes = imageRes;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public String getGenre() { return genre; }
    public String getDescription() { return description; }
    public int getImageRes() { return imageRes; }
}