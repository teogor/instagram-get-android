package com.dolphpire.instamanage.getfollowersfragment.model;

public class ModelGetFollowers {

    private int coins;
    private int followers;

    public ModelGetFollowers(int payAmount, int receiveAmount) {
        this.coins = payAmount;
        this.followers = receiveAmount;
    }

    public int getCoins() {
        return coins;
    }

    public int getFollowers() {
        return followers;
    }

}
