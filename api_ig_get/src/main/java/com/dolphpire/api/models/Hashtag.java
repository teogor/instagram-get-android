package com.dolphpire.api.models;

public class Hashtag {

    private String hashtag = "";
    private int number_posts = 0;
    private int type = 0;

    public Hashtag() {

    }

    public String getHashtag() {
        return "#" + hashtag;
    }

    public int getNumberPosts() {
        return number_posts;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
