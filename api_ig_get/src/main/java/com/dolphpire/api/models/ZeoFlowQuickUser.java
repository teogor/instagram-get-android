package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZeoFlowQuickUser {

    private int user_id = 0;
    private int account_private = 0;
    private int account_verified = 0;
    private int account_online = 0;
    private int show_user_online = 0;
    private int is_following = 0;
    private int is_requested = 0;

    private String username = "";
    private String name = "";
    private String image = "";

    private String timestamp;

    ZeoFlowQuickUser() {

    }

    public Timestamp getTimestamp() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(timestamp);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return username;
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