package com.example.root.memternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.Display;

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
    private static String getJSON(long startId, int count) {
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

    private static ArrayList<String> getUrls(long startId, int count) {
        Log.d("async", "begin");
        String server_resp = getJSON(startId, count);
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
        Log.d("async", "end");
        return test;
    }

    public synchronized static void getMemes(Long startId, Integer count, ArrayList<Meme> to) {
        ArrayList<Meme> newMemes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newMemes.add(new Meme());
        }
        (new MemeDownloader()).execute(startId, count, newMemes);
        to.addAll(newMemes);
    }

    public static void getMemes(int count, ArrayList<Meme> to) {
        getMemes(lastId, count, to);
    }

    private static class MemeDownloader extends AsyncTask<Object, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("async", "OK");
        }

        @Override
        protected Void doInBackground(Object... params) {
            Long startId = (Long) params[0];
            Integer count = (Integer) params[1];
            List<Meme> memes = (List<Meme>) params[2];
            ArrayList<String> urls = Loader.getUrls(startId, count);
            for (int i = 0; i < urls.size(); i++) {
                try {
                    URL url = new URL(urls.get(i));
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                    memes.get(i).setImg(bitmap);
                    memes.get(i).setUrl(urls.get(0));
                    memes.get(i).setId(lastId + urls.size() - i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
