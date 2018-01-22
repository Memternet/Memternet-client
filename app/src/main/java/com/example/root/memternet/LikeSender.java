package com.example.root.memternet;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class LikeSender {
    static final String LIKE_URL = "http://memes.kotim.ru/like/";

    public static void sendLike(final long id, final int score) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... foo) {
                try {
                    URL url = new URL(LIKE_URL + String.valueOf(id));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "Token " + Token.id);
                    String data = "score=" + String.valueOf(score);
                    byte[] toSend = data.getBytes();
                    //connection.setRequestProperty("Content-Length", String.valueOf(toSend.length));
                    connection.getOutputStream().write(toSend);
                    toSend = null;
                    connection.connect();
                    connection.getResponseCode();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
