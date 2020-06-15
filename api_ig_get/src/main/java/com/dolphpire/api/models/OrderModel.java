package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel implements Serializable
{

    private int user_id = 0;
    private int interactions_count = 0;
    private int target = 0;
    private int type = 0;
    private long ig_account_id;
    private long post_id;
    private String post_image;
    private String created_at = "";

    public OrderModel()
    {

    }

    public int getUUID()
    {
        return user_id;
    }

    public int getInteractionCount()
    {
        return interactions_count;
    }

    public int getType()
    {
        return type;
    }

    public int getTarget()
    {
        return target;
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

    public Timestamp getCreatedAt() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(created_at);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}