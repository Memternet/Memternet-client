package com.example.root.memternet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MemeView extends CardView {
    TextView memeText;
    ImageView img;

    MemeView(Context context) {
        super(context);
        final int margin = (int) (16 * context.getResources().getDisplayMetrics().density);
        img = new ImageView(context);
        setBackgroundColor(0xb3d9ff);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin / 4, margin, margin / 4);
        super.addView(img, lp);
        super.setLayoutParams(lp);

    }

    public void setMeme(final Meme meme) {
        if (meme.getImg() == null)
            return;
        final int margin = (int) (16 * getContext().getResources().getDisplayMetrics().density);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        Bitmap bitmap = meme.getImg();
        double k = (double) displayMetrics.widthPixels / bitmap.getWidth();
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (k * bitmap.getWidth()),
                (int) (k * bitmap.getHeight()), false);
        img.setImageBitmap(bitmap);
        final Button button1 = new Button(getContext());
        final Button button2 = new Button(getContext());
        if (meme.getState() == Meme.LIKED) {
            button1.setBackground(getResources().getDrawable(R.drawable.liked));
            button2.setBackground(getResources().getDrawable(R.drawable.downvote));
        } else if (meme.getState() == Meme.DISLIKED) {
            button1.setBackground(getResources().getDrawable(R.drawable.upvote));
            button2.setBackground(getResources().getDrawable(R.drawable.disliked));
        } else {
            button1.setBackground(getResources().getDrawable(R.drawable.upvote));
            button2.setBackground(getResources().getDrawable(R.drawable.downvote));
        }
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meme.getState() != Meme.LIKED) {
                    button1.setBackground(getResources().getDrawable(R.drawable.liked));
                    meme.setState(Meme.LIKED);
                }
                else {
                    button1.setBackground(getResources().getDrawable(R.drawable.upvote));
                    meme.setState(Meme.OTHER);
                }
                button2.setBackground(getResources().getDrawable(R.drawable.downvote));
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meme.getState() != Meme.DISLIKED) {
                    button2.setBackground(getResources().getDrawable(R.drawable.disliked));
                    meme.setState(Meme.DISLIKED);
                }
                else {
                    button2.setBackground(getResources().getDrawable(R.drawable.downvote));
                    meme.setState(Meme.OTHER);
                }
                button1.setBackground(getResources().getDrawable(R.drawable.upvote));
            }
        });
        int size = (int) (getContext().getResources().getDisplayMetrics().density * 25);
        LayoutParams gen = new LayoutParams(bitmap.getWidth(), bitmap.getHeight() + size);
        this.setLayoutParams(gen);
        LayoutParams lp = new LayoutParams(size, size, Gravity.BOTTOM);
        lp.setMargins(margin, 0, 0, margin / 3);
        super.addView(button1, lp);
        LayoutParams lp2 = new LayoutParams(size, size, Gravity.BOTTOM);
        lp2.setMargins(2 * margin + size, 0, 0, 0);
        super.addView(button2, lp2);
    }
}
