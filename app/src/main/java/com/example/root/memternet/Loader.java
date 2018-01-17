package com.example.root.memternet;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loader {
    private static long lastId = -1;
    private static final String SERVER_ADR = "http://memes.kotim.ru/memes/";
    private static class URLDownloader extends AsyncTask<Long, Void, String>
    {
        @Override
        protected String doInBackground(Long... longs) {
            long startId = longs[0];
            long count = longs[1];
            String ip = SERVER_ADR;
            String server_resp;
            try {
                String startIdString = "";
                if (startId != -1)
                    startIdString = "start_id=" + String.valueOf(startId) + "&";
                URL url = new URL(ip + "?" + startIdString +
                        "count=" +
                        String.valueOf(count));
                URLConnection connection = url.openConnection();
                connection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null)
                    builder.append(line);
                in.close();
                server_resp = builder.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            return server_resp;
        }
    }

    private static ArrayList<String> getUrls(long startId, int count) {
        URLDownloader loader = new URLDownloader();
        loader.execute(startId, (long) count);
        String server_resp = null;
        try {
            server_resp = loader.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        MemeList memes = MemeList.parse(server_resp);
        if (memes == null)
            return new ArrayList<>();
        List<MemeList.MemeObj> memesList = Arrays.asList(memes.getMemes());

        ArrayList<String> test = new ArrayList<>();

        for (int i = 0; i < memesList.size(); i++) {
            if (lastId == -1 || lastId >= memesList.get(i).getId())
                lastId = memesList.get(i).getId() - 1;
            test.add(memesList.get(i).getImg_url());
        }
        return test;
    }

    public static ArrayList<Meme> getMemes(long startId, int count) {
        ArrayList<String> stringList = getUrls(startId, count);
        if (stringList == null)
            return null;
        ArrayList<Meme> memes = new ArrayList<>();
        for (int i = 0; i < Math.min(count, stringList.size()); i++) {
            memes.add(new Meme(startId - i, stringList.get(i), null));
        }
        Pair<ArrayList<String>, ArrayList<Meme>> pair = new Pair<>(stringList, memes);
        (new MemeDownloader()).execute(pair);
        return memes;
    }

    public static ArrayList<Meme> getMemes(int count) {
        return getMemes(lastId, count);
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
        protected Void doInBackground(Pair<ArrayList<String>, ArrayList<Meme>>... lists) {
            ArrayList<String> urls = lists[0].first;
            ArrayList<Meme> memes = lists[0].second;
            for (int i = 0; i < urls.size(); i++) {
                try {
                    URL url = new URL(urls.get(i));
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    memes.get(i).setImg(BitmapFactory.decodeStream(connection.getInputStream()));
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
