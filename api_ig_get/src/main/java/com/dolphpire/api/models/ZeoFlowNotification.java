package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZeoFlowNotification {


    private int type = 0;
    private int user_id = 0;
    private int post_id = 0;
    private int comment_id = 0;
    private int account_private = 0;
    private int is_following = 0;
    private int is_requested = 0;
    private int account_verified = 0;
    private int post_type = -1;
    private int saw = 0;
    private int interaction_type = 0;
    private int number_likes = 0;
    private int number_unlikes = 0;
    private int number_views = 0;
    private int comment_liked = 0;
    private int comment_unliked = 0;
    private int comment_number_likes = 0;
    private int comment_number_unlikes = 0;

    private String username = "";
    private String post_description = "";
    private String name = "";
    private String image = "";
    private String post_images_url = "";
    private String post_color_1 = "";
    private String post_color_2 = "";
    private String comment = "";
    private String post_date = "";

    private String timestamp;
    private long timestampDiff;

    public ZeoFlowNotification(long timestampDiff, String timestamp) {
        this.timestampDiff = timestampDiff;
        this.timestamp = timestamp;
        this.type = 0;
    }

    public String getPostTimestamp() {
        return post_date;
    }

    public Timestamp getPostDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(post_date);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getTimestampDiff() {
        return timestampDiff;
    }

    public int getPostType() {
        return post_type;
    }

    public int getInteractionType() {
        return interaction_type;
    }

    public int getPostLikes() {
        return number_likes;
    }

    public int getPostUnlikes() {
        return number_unlikes;
    }

    public int getCommentLikes() {
        return comment_number_likes;
    }

    public int getCommentUnlikes() {
        return comment_number_unlikes;
    }

    public boolean getCommentLiked() {
        return comment_liked == 1;
    }

    public boolean getCommentUnliked() {
        return comment_unliked == 1;
    }

    public int getPostViews() {
        return number_views;
    }

    public int getCommentID() {
        return comment_id;
    }

    public int getPostID() {
        return post_id;
    }

    public int getNotifyType() {
        return type;
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

    public String getCommentTimestamp() {
        return timestamp;
    }

    public String getDateAgo() {
        return timestamp;
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

    public String getPostDescription() {
        return post_description;
    }

    public String getPostColor1() {
        return "#" + post_color_1;
    }

    public String getPostColor2() {
        return "#" + post_color_2;
    }

    public String getPostImagesURL() {
        return post_images_url;
    }

    public String getComment() {
        return comment;
    }

    public boolean getSeen() {
        return saw == 1;
    }

    public boolean getAccountVerified() {
        return account_verified == 1;
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