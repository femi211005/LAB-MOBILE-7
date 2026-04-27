package com.anugrah.libraryapp;

import android.net.Uri;

public class Book {
    private String judul;
    private String penulis;
    private String tahunTerbit;
    private String blurb;
    private int gambarRes;
    private Uri gambarUri;
    private boolean isLiked;

    private String genre;
    private float rating;

    public Book(String judul, String penulis, String tahunTerbit, String blurb, int gambarRes, String genre, float rating) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.blurb = blurb;
        this.gambarRes = gambarRes;
        this.isLiked = false;
        this.genre = genre;
        this.rating = rating;
    }

    public Book(String judul, String penulis, String tahunTerbit, String blurb, Uri gambarUri, String genre) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.blurb = blurb;
        this.gambarUri = gambarUri;
        this.isLiked = false;
        this.genre = genre;
        this.rating = 0.0f;
    }

    public String getJudul() { return judul; }
    public String getPenulis() { return penulis; }
    public String getTahunTerbit() { return tahunTerbit; }
    public String getBlurb() { return blurb; }
    public int getGambarRes() { return gambarRes; }
    public Uri getGambarUri() { return gambarUri; }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
    public String getGenre() { return genre; }
    public float getRating() { return rating; }
}