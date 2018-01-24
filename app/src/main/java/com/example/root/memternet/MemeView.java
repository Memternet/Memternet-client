package com.example.root.memternet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MemeView extends CardView {
    TextView memeText;
    ImageView img;
    final Runnable update;
    App app;

    MemeView(Context context, Runnable update) {
        super(context);
        app = (App) context.getApplicationContext();
        this.update = update;
        final int margin = (int) (16 * context.getResources().getDisplayMetrics().density);
        img = new ImageView(context);
        setBackgroundColor(0xb3d9ff);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin / 4, margin, margin / 4);
        super.addView(img, lp);
        super.setLayoutParams(lp);
        memeText = new TextView(context);
        img = new ImageView(context);
        super.setBackgroundColor(/*getResources().getColor(R.color.lightOrange)*/0xb3d9ff);
        Button button = new Button(context);
        //super.addView(button, 200, 200);
        setElevation(100);
        super.addView(memeText);
        super.addView(img);
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
        if (meme.getMy_score() == Meme.LIKED) {
            button1.setBackground(getResources().getDrawable(R.drawable.liked));
            button2.setBackground(getResources().getDrawable(R.drawable.downvote));
        } else if (meme.getMy_score() == Meme.DISLIKED) {
            button1.setBackground(getResources().getDrawable(R.drawable.upvote));
            button2.setBackground(getResources().getDrawable(R.drawable.disliked));
        } else {
            button1.setBackground(getResources().getDrawable(R.drawable.upvote));
            button2.setBackground(getResources().getDrawable(R.drawable.downvote));
        }
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meme.getMy_score() != Meme.LIKED) {
                    button1.setBackground(getResources().getDrawable(R.drawable.liked));
                    meme.setRating(meme.getRating() - meme.getMy_score() + Meme.LIKED);
                    memeText.setText(String.valueOf(meme.getRating()));
                    meme.setMy_score(Meme.LIKED);
                    LikeSender.sendLike(app, meme.getId(), 1);
                }
                else {
                    button1.setBackground(getResources().getDrawable(R.drawable.upvote));
                    meme.setRating(meme.getRating() - meme.getMy_score());
                    memeText.setText(String.valueOf(meme.getRating()));
                    meme.setMy_score(Meme.OTHER);
                    LikeSender.sendLike(app, meme.getId(), 0);
                }
                button2.setBackground(getResources().getDrawable(R.drawable.downvote));
                update.run();
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meme.getMy_score() != Meme.DISLIKED) {
                    button2.setBackground(getResources().getDrawable(R.drawable.disliked));
                    meme.setRating(meme.getRating() - meme.getMy_score() + Meme.DISLIKED);
                    memeText.setText(String.valueOf(meme.getRating()));
                    meme.setMy_score(Meme.DISLIKED);
                    LikeSender.sendLike(app, meme.getId(), -1);
                }
                else {
                    button2.setBackground(getResources().getDrawable(R.drawable.downvote));
                    meme.setRating(meme.getRating() - meme.getMy_score());
                    memeText.setText(String.valueOf(meme.getRating()));
                    meme.setMy_score(Meme.OTHER);
                    LikeSender.sendLike(app, meme.getId(), 0);
                }
                button1.setBackground(getResources().getDrawable(R.drawable.upvote));
                update.run();
            }
        });
        int size = (int) (getContext().getResources().getDisplayMetrics().density * 25);
        LayoutParams gen = new LayoutParams(bitmap.getWidth(), bitmap.getHeight() + 2 * size);
        this.setLayoutParams(gen);
        LayoutParams lp = new LayoutParams(size, size, Gravity.BOTTOM);
        lp.setMargins(margin, 0, 0, margin / 3);
        addView(button1, lp);
        LayoutParams lp2 = new LayoutParams(size, size, Gravity.BOTTOM);
        lp2.setMargins(2 * margin + size, 0, 0, 0);
        addView(button2, lp2);
        TextView textView = new TextView(getContext());
        LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM);
        lp3.setMargins(3 * margin + 2 * size, 0, 0, margin / 3);
        textView.setText(String.valueOf(meme.getRating()));
        textView.setTextSize(15);
        addView(textView, lp3);
    }
}
