package com.example.root.memternet;

import android.util.Log;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieSyncManager;


import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MemeAdapter adapter;
    RecyclerView posts;
    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App) getApplicationContext();
        posts = findViewById(R.id.posts);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        posts.setLayoutManager(lm);
        adapter = (MemeAdapter) getLastCustomNonConfigurationInstance();
        if (adapter == null)
            adapter = new MemeAdapter(app);
        posts.setAdapter(adapter);
        final Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (((MemeAdapter)posts.getAdapter()).isNeedUpdate())
                    ((MemeAdapter)posts.getAdapter()).update();
            }
        };
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                h.sendEmptyMessage(0);
            }
        };
        Timer t = new Timer();
        t.schedule(tt, 100, 100);
        startActivity(new Intent(this, AuthorisationActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return adapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("mymenu", String.valueOf(item.getTitle()));
        if (item.getItemId() == R.id.last) {
            adapter = new MemeAdapter(app, false);
            posts.setAdapter(adapter);
        }
        else if (item.getItemId() == R.id.top) {
            adapter = new MemeAdapter(app, true);
            posts.setAdapter(adapter);
        }
        else {
            app.mGoogleSignInClient.signOut();
            startActivity(new Intent(this, AuthorisationActivity.class));
        }
        return false;
    }
}
