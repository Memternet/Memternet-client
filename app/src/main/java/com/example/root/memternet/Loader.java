package com.example.root.memternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loader {
    private static final String SERVER_ADR = "http://memes.kotim.ru/";
    private static long last_id = -1, offset = 0;

    private static String getJSON(String req, App app) {
        try {
            Log.d("token", app.getId());
            URL url = new URL(req);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Authorization", "Token " + app.getId());
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
                builder.append(line);
            in.close();
            Log.d("token", "ok");
            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "{\"memes\":[]}";
        }
    }

    private static String makeTopReq(int count) {
        String req = SERVER_ADR + "top/";
        req += "?count=" + String.valueOf(count);
        req += "&offset=" + String.valueOf(offset);
        return req;
    }

    private static String makeLastReq(int count) {
        String req = SERVER_ADR + "memes/";
        req += "?count=" + String.valueOf(count);
        if (last_id != -1) {
            req += "&start_id=" + String.valueOf(last_id);
        }
        return req;
    }

    public static void getMemes(App app, int count, ArrayList<Meme> to, boolean sortByRating) {
        if (!app.isStarted())
            return;
        (new ImgDownloader()).execute(app, Integer.valueOf(count), to, Boolean.valueOf(sortByRating));
    }

    private static class ImgDownloader extends AsyncTask<Object, Void, List<Meme>[]> {

        @Override
        protected List<Meme>[] doInBackground(Object... params) {
            App app = (App) params[0];
            Integer count = (Integer) params[1];
            ArrayList<Meme> to = (ArrayList<Meme>) params[2];
            Boolean sortByRating = (Boolean) params[3];
            String server_resp;
            if (sortByRating)
                server_resp = getJSON(makeTopReq(count), app);
            else
                server_resp = getJSON(makeLastReq(count), app);
            List<Meme> memes = Arrays.asList(MemeList.parse(server_resp).getMemes());
            if (sortByRating)
                offset += memes.size();
            for (int i = 0; i < memes.size(); i++) {
                try {
                    URL url = new URL(memes.get(i).getImg_url());
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    Log.d("bmp", "begin");
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                    Log.d("bmp", "end");
                    memes.get(i).setImg(bitmap);
                    if (!sortByRating && (last_id == -1 || last_id >= memes.get(i).getId())) {
                        last_id = memes.get(i).getId() - 1;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<Meme>[] ans = new List[2];
            ans[0] = to;
            ans[1] = memes;
            return ans;
        }

        @Override
        protected void onPostExecute(List<Meme>[] memes) {
            super.onPostExecute(memes);
            memes[0].addAll(memes[1]);
        }
    }
}
