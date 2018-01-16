package com.example.root.memternet;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

public class MemeView extends CardView {
    TextView memeText;
    ImageView img;
    MemeView (Context context) {
        super(context);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        super.setLayoutParams(lp);
        memeText = new TextView(context);
        img = new ImageView(context);
        setElevation(100);
        super.addView(memeText);
        super.addView(img);
    }
    public void setMeme(Meme meme) {
        img.setImageBitmap(meme.getImage());
        //memeText.setText("Meme! " + String.valueOf(Math.random()));
    }
}
