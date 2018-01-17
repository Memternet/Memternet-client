package com.example.root.memternet;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder> {
    ArrayList<Meme> data;
    boolean needUpdate = false;
    public boolean isNeedUpdate() {
        return needUpdate;
    }
    private int displayWidth;

    MemeAdapter(int w) {
        super();
        data = new ArrayList();
        displayWidth = w;
    }
    private static final int MEMECOUNT = 5;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        MemeView meme;
        ViewHolder (MemeView m) {
            super(m);
            meme = m;
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
        if (position >= data.size()) {
            needUpdate = true;
            Loader.setDisplayWidth(displayWidth);
            ArrayList<Meme> newMemes = (ArrayList<Meme>) Loader.getMemes(MEMECOUNT);
            for (int i = 0; i < newMemes.size(); i++)
            {
                data.add(newMemes.get(i));
            }
        }
        if (position < data.size())
            holder.meme.setMeme(data.get(position));
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
