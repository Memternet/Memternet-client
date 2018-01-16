package com.example.root.memternet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class MemeList {
    static class MemeObj {
        int id;
        String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
    MemeObj[] memes;

    public MemeObj[] getMemes() {
        return memes;
    }

    public void setMemes(MemeObj[] memes) {
        this.memes = memes;
    }

    public static MemeList parse(String json) {
        ObjectMapper mapper = new ObjectMapper();
        MemeList ans = null;
        try {
            ans = mapper.readValue(json, MemeList.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
