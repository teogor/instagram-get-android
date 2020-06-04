package com.dolphpire.api.models;

import java.io.Serializable;

public class AboutFeaturedProfileModel implements Serializable {

    private String username = "";
    private String name = "";
    private String image = "";

    private int user_id = 0;
    private int is_requested = 0;
    private int is_following = 0;
    private int account_online = 0;
    private int account_private = 0;
    private int account_verified = 0;

    public AboutFeaturedProfileModel() {

    }

    public int getUserId() {
        return user_id;
    }

    public boolean getIsFollowing() {
        return is_following == 1;
    }

    public boolean getIsRequested() {
        return is_requested == 1;
    }

    public boolean getAccountVerified() {
        return account_verified == 1;
    }

    public boolean getAccountOnline() {
        return account_online == 1;
    }

    public boolean getAccountPrivate() {
        return account_private == 1;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
