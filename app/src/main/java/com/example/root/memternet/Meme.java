package com.example.root.memternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public final class Meme {

    static private ArrayList<Meme> memes;
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
        List<String> stringList = getUrls(startId, count);
        if (stringList == null)
            return null;
        memes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            memes.add(new Meme(startId - i, stringList.get(i), null));
        }
        (new MemeDownloader()).execute(stringList.toArray(new String[count]));
        return memes;
    }

    public Bitmap getImage() {
        return img;
    }

    private static class MemeDownloader extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String[] urls) {
            for (int i = 0; i < urls.length; i++) {
                try {
                    URL url = new URL(urls[i]);
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
