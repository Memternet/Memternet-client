package com.example.root.memternet;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by root on 13.01.18.
 */

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
    private static final int MEMECOUNT = 1;
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
            ArrayList<Meme> newMemes = (ArrayList<Meme>) Meme.getMemes(0, MEMECOUNT);
            for (int i = 0; i < newMemes.size(); i++)
            {
                data.add(newMemes.get(i));
            }
        }
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
