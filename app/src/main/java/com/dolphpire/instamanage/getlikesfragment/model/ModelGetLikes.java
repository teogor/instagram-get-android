package com.dolphpire.instamanage.getlikesfragment.model;

public class ModelGetLikes {

    private int coins;
    private int likes;

    public ModelGetLikes(int payAmount, int receiveAmount) {
        this.coins = payAmount;
        this.likes = receiveAmount;
    }

    public int getCoins() {
        return coins;
    }

    public int getLikes() {
        return likes;
    }

}
