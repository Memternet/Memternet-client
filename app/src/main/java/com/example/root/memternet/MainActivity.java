package com.example.root.memternet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Meme> memes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memes = Meme.getMemes(5, 3);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView1 = findViewById(R.id.imageView1);
                ImageView imageView2 = findViewById(R.id.imageView2);
                ImageView imageView3 = findViewById(R.id.imageView3);
                imageView1.setImageBitmap(memes.get(0).getImage());
                imageView2.setImageBitmap(memes.get(1).getImage());
                imageView3.setImageBitmap(memes.get(2).getImage());
            }
        });
    }
}
