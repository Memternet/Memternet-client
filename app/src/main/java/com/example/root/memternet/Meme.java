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

    public final String url;
    public final long id;
    private Bitmap img;

    Meme(long id, String url) {
        this.id = id;
        this.url = url;
        (new MemeDownloader(new MemeDownloader.OnImageComplete() {
            @Override
            public void onComplete(Bitmap image) {
                img = image;
            }
        })).execute(url);
    }

    private static ArrayList<String> getUrls(long startId, int count) {
        //TODO
        return null;
    }

    public static ArrayList<Meme> getMemes(long startId, int count) {
        List<String> stringList = getUrls(startId, count);
        if (stringList == null)
            return null;
        ArrayList<Meme> memeArrayList = new ArrayList<>();
        for (String url : stringList) {
            memeArrayList.add(new Meme(startId, url));
            startId--;
        }
        return memeArrayList;
    }

    Bitmap getImage() {
        return img;
    }

    private static class MemeDownloader extends AsyncTask<String, Void, Bitmap> {

        private final OnImageComplete onImageComplete;

        private MemeDownloader(OnImageComplete onImageComplete) {
            this.onImageComplete = onImageComplete;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                if (onImageComplete != null)
                    onImageComplete.onComplete(bitmap);
            }
        }


        @Override
        protected Bitmap doInBackground(String... urls) {
            if (urls[0] == null)
                return null;
            try {
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                return BitmapFactory.decodeStream(connection.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private interface OnImageComplete {
            void onComplete(Bitmap image);
        }
    }
}
