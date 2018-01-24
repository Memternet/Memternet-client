package com.example.root.memternet;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder> {
    ArrayList<Meme> data;
    boolean needUpdate = false, sortByRating;
    App app;
    public boolean isNeedUpdate() {
        return needUpdate;
    }

    MemeAdapter(App app, boolean sortByRating) {
        super();
        this.app = app;
        data = new ArrayList();
        this.sortByRating = sortByRating;
    }

    MemeAdapter(App app) {
        this(app, false);
    }

    private static final int MEMECOUNT = 5;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        MemeView meme;
        //TextView t;
        ProgressBar pb = null;
        ViewHolder (MemeView m) {
            super(m);
            meme = m;
            //t = m;
        }
    }

    @Override
    public MemeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        MemeView v = new MemeView(parent.getContext(), new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
        //TextView t = new TextView(parent.getContext());
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("size", String.valueOf(data.size()));
        holder.setIsRecyclable(false);
        if (position >= data.size() && !needUpdate) {
            needUpdate = true;
            //Log.d("test", "test");
            Loader.getMemes(app, MEMECOUNT, data, sortByRating);
        }
        else if (position < data.size()) {
            holder.meme.setMeme(data.get(position));
            //holder.meme.memeText.setText(String.valueOf(position));
            //holder.t.setText(String.valueOf(position));
        }
    }

    public void update() {
        notifyDataSetChanged();
        needUpdate = false;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size() + 1;
    }
}
