package com.example.root.memternet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 13.01.18.
 */

public class Meme {

    public static List<Meme> loadMemes(int count) {
        //TODO: Make normal behavior
        ArrayList<Meme> ans = new ArrayList();
        for (int i = 0; i < count; i++) {
            Meme newMeme = new Meme();
            ans.add(newMeme);
        }
        return ans;
    }
}
