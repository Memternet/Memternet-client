package com.example.root.memternet;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MemeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView posts = findViewById(R.id.posts);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        posts.setLayoutManager(lm);
        adapter = (MemeAdapter) getLastCustomNonConfigurationInstance();
        if (adapter == null)
            adapter = new MemeAdapter();
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
//        ImageLoader loader;
//        loader.start
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return adapter;
    }
}
