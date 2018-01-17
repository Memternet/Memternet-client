package com.example.root.memternet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
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
        super.setElevation(50);
        super.setLayoutParams(lp);
        memeText = new TextView(context);
        img = new ImageView(context);
        super.setBackgroundColor(/*getResources().getColor(R.color.lightOrange)*/0xb3d9ff);
        Button button = new Button(context);
        //super.addView(button, 200, 200);
        setElevation(100);
        //super.addView(memeText);
        super.addView(img);

    }
    public void setMeme(Meme meme) {
        if (meme.getImg() == null)
            return;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        Bitmap bitmap = meme.getImg();
        double k = (double)displayMetrics.widthPixels / bitmap.getWidth();
        img.setImageBitmap(Bitmap.createScaledBitmap(bitmap,(int)(k * bitmap.getWidth()),
                (int)(k * bitmap.getHeight()), false));
        memeText.setText("Meme #" + String.valueOf(meme.getId()));
        //memeText.setText("Meme! " + String.valueOf(Math.random()));
    }
}
