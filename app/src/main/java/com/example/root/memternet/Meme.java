package com.example.root.memternet;

import android.graphics.Bitmap;
import android.view.Display;

public final class Meme {
    private String url;
    private long id;
    private Bitmap img;
    final public static int LIKED = 0, DISLIKED = 1, OTHER = 2;
    private int state = OTHER;

    public Meme(long id, String url, Bitmap bitmap) {
        this.id = id;
        this.url = url;
        this.img = bitmap;
    }

    public void setState(int s)
    {
        state = s;
    }

    public int getState()
    {
        return state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }
}
