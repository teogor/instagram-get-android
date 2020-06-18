package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedModel implements Serializable
{

    private long order_id;
    private int type;
    private long post_id;
    private long ig_account_id;
    private String profile_picture;
    private String post_image;

    public FeedModel()
    {

    }

    public long getOrderID()
    {
        return order_id;
    }

    public int getType()
    {
        return type;
    }

    public long getIGAccountID()
    {
        return ig_account_id;
    }

    public long getPostID()
    {
        return post_id;
    }

    public String getPostImage()
    {
        return post_image;
    }

    public String getProfilePicture()
    {
        return profile_picture;
    }

}