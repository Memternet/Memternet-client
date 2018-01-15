package com.example.root.memternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public final class Meme {

    public final String url;
    public final long id;
    private Bitmap img;

    public Meme(long id, String url, Bitmap bitmap) {
        this.id = id;
        this.url = url;
        this.img = bitmap;
    }

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
    }

    public Bitmap getImage() {
        return img;
    }

    private static class MemeDownloader extends AsyncTask<Pair<ArrayList<String>, ArrayList<Meme>>,
            Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Pair<ArrayList<String>, ArrayList<Meme>>[] lists) {
            ArrayList<String> urls = lists[0].first;
            ArrayList<Meme> memes = lists[0].second;
            for (int i = 0; i < urls.size(); i++) {
                try {
                    URL url = new URL(urls.get(i));
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    memes.get(i).img = BitmapFactory.decodeStream(connection.getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
