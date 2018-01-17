package com.example.root.memternet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MemeView extends CardView {
    TextView memeText;
    ImageView img;
    MemeView (Context context) {
        super(context);
        final int margin = (int)(16 * context.getResources().getDisplayMetrics().density);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin / 2, margin, margin / 2);
        setElevation(50);
        super.setLayoutParams(lp);
        memeText = new TextView(context);
        img = new ImageView(context);
        Button button = new Button(context);
        //super.addView(button, 200, 200);
        setElevation(100);
        super.addView(memeText);
        super.addView(img);

    }
    public void setMeme(Meme meme) {
        img.setImageBitmap(meme.getImg());
        memeText.setText("Meme #" + String.valueOf(meme.getId()));
        //memeText.setText("Meme! " + String.valueOf(Math.random()));
    }
}
