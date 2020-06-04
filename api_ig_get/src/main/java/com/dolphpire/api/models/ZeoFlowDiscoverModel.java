package com.dolphpire.api.models;

public class ZeoFlowDiscoverModel {

    private int id = 0;
    private int type = 0;
    private int account_private = 0;
    private int account_verified = 0;
    private int account_online = 0;
    private int show_user_online = 0;
    private int is_following = 0;
    private int is_requested = 0;
    private int hashtag_size = 0;

    private String username = "";
    private String name = "";
    private String image = "";
    private String hashtag = "";
    private String word = "";

    ZeoFlowDiscoverModel() {

    }

    public int getType() {
        return type;
    }

    public int getID() {
        return id;
    }

    public int getHashtagSize() {
        return hashtag_size;
    }

    public String getUsername() {
        return "@" + username;
    }

    public String getWord() {
        return word;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getHashtag() {
        return "#" + hashtag;
    }

    public boolean getAccountVerified() {
        return account_verified == 1;
    }

    public boolean getAccountOnline() {
        return account_online == 1;
    }

    public boolean getShowAccountOnline() {
        return show_user_online == 1;
    }

    public boolean getIsFollowing() {
        return is_following == 1;
    }

    public boolean getIsRequested() {
        return is_requested == 2;
    }

    public boolean getAccountPrivate() {
        return account_private == 1;
    }

    public void setFollowing(boolean newMode) {
        if (newMode) this.is_following = 1;
        else this.is_following = 0;
    }

    public void setRequested(boolean newMode) {
        if (newMode) this.is_requested = 2;
        else this.is_requested = 0;
    }

}