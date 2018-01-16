package com.example.root.memternet;

import android.graphics.Bitmap;
<<<<<<< HEAD
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
=======
>>>>>>> 5d3c49465d00fa36723c439190af95de2bbf5303

public final class Meme {
    public final String url;
    public final long id;
    private Bitmap img;

    public Meme(long id, String url, Bitmap bitmap) {
        this.id = id;
        this.url = url;
        this.img = bitmap;
    }

<<<<<<< HEAD
    private static ArrayList<String> getUrls(long startId, int count) {
        //TODO
        ArrayList<String> test = new ArrayList<>();
        test.add("https://memepedia.ru/wp-content/uploads/2016/08/med_1426704578_00014.jpg");
        test.add("http://vsekidki.ru/uploads/posts/2016-01/1453764864_l_c0788aa1.jpg");
        test.add("http://8uh.ru/wp-content/uploads/2015/10/chto-takoe-kek-v-vkontakte.jpg");
        return test;
    }

    public static ArrayList<Meme> getMemes(long startId, int count) {
        ArrayList<String> stringList = getUrls(startId, count);
        if (stringList == null)
            return null;
        ArrayList<Meme> memes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            memes.add(new Meme(startId - i, stringList.get(i), null));
        }
        Pair<ArrayList<String>, ArrayList<Meme>>[] pair = new Pair[1];
        pair[0] = new Pair<>(stringList, memes);
        (new MemeDownloader()).execute(pair);
        return memes;
=======
    public void setImg(Bitmap img) {
        this.img = img;
>>>>>>> 5d3c49465d00fa36723c439190af95de2bbf5303
    }

    public Bitmap getImage() {
        return img;
    }
}
