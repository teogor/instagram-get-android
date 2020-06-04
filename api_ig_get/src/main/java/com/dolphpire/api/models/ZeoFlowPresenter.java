package com.dolphpire.api.models;

public class ZeoFlowPresenter {

    private String name = "";
    private String username = "";
    private String image = "";
    private int user_id = 0;
    private int account_online = 0;
    private int account_verified = 0;
    private int show_user_online = 0;
    private String hashtag = "";
    private int number_posts = 0;
    private int type = 0;

    public ZeoFlowPresenter() {

    }

    public String getHashtag() {
        return "#" + hashtag;
    }

    public int getNumberPosts() {
        return number_posts;
    }

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return "@" + username;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public boolean getAccountVerified() {
        return account_verified == 1;
    }

    public boolean getAccountOnline() {
        return account_online == 1;
    }

    public boolean getShowUserOnline() {
        return show_user_online == 1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
