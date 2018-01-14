package com.example.root.memternet;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.TextView;

public class MemeView extends CardView {
    TextView memeText;
    MemeView (Context context) {
        super(context);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        super.setLayoutParams(lp);
        memeText = new TextView(context);
        super.addView(memeText);
    }
    public void setMeme(Meme meme) {
        memeText.setText("Meme! " + String.valueOf(Math.random()));
    }
}
