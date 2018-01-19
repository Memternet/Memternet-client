package com.example.root.memternet;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder> {
    ArrayList<Meme> data;
    boolean needUpdate = false;
    public boolean isNeedUpdate() {
        return needUpdate;
    }

    MemeAdapter() {
        super();
        data = new ArrayList();
    }
    private static final int MEMECOUNT = 5;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        MemeView meme;
        //TextView t;
        ViewHolder (MemeView m) {
            super(m);
            meme = m;
            //t = m;
        }
    }

    @Override
    public MemeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        MemeView v = new MemeView(parent.getContext());
        //TextView t = new TextView(parent.getContext());
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position >= data.size() && !needUpdate) {
            needUpdate = true;
            Log.d("test", "test");
            Loader.getMemes(MEMECOUNT, data);
        }
        else if (position < data.size())
            holder.meme.setMeme(data.get(position));
            //holder.t.setText(String.valueOf(position));
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
