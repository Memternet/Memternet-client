package com.example.root.memternet;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Meme {
    private String img_url;
    private long id;
    @JsonIgnore
    private Bitmap img;
    private int rating, my_score;
    final public static int LIKED = 1, DISLIKED = -1, OTHER = 0;

    public Meme(long id, String img_url, Bitmap bitmap) {
        this.id = id;
        this.img_url = img_url;
        this.img = bitmap;
    }

    Meme() {
        this.id = 0;
        this.img_url = null;
        this.img = null;
    }

    public void setMy_score(int my_score) {
        this.my_score = my_score;
    }

    public int getRating() {
        return rating;
    }

    public int getMy_score() {
        return my_score;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }
}
