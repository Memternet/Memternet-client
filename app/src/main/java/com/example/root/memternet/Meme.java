package com.example.root.memternet;

import android.graphics.Bitmap;
import android.view.Display;

public final class Meme {
    public final String url;
    public final long id;
    private Bitmap img;

    public Meme(long id, String url, Bitmap bitmap) {
        this.id = id;
        this.url = url;
        this.img = bitmap;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getImage() {
        return img;
    }
}
