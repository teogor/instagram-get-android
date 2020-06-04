package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZeoFlowComment implements Serializable {

    private String username = "";
    private String name = "";
    private String image = "";
    private String comment = "";

    private int owner_id = 0;
    private int comment_id = 0;
    private int post_id = 0;
    private int account_verified = 0;
    private int number_likes = 0;
    private int number_unlikes = 0;
    private int liked = 0;
    private int unliked = 0;
    private int type = 0;

    private String timestamp = "";

    public ZeoFlowComment() {

    }

    public ZeoFlowComment(String username, String image, String name, String comment, int account_verified, int owner_id, int post_id, String datePosted, int type) {
        this.username = username;
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.owner_id = owner_id;
        this.account_verified = account_verified;
        this.post_id = post_id;
        this.timestamp = datePosted;
        this.type = type;
    }

    public ZeoFlowComment(String username, String image, String name, String comment, int account_verified, int owner_id, int post_id, String datePosted, int number_likes, int number_unlikes, boolean liked, boolean unliked, int type) {
        this.username = username;
        this.image = image;
        this.name = name;
        this.comment = comment;
        this.owner_id = owner_id;
        this.account_verified = account_verified;
        this.post_id = post_id;
        this.timestamp = datePosted;
        this.type = type;
        this.number_likes = number_likes;
        this.number_unlikes = number_unlikes;
        this.liked = liked ? 1 : 0;
        this.unliked = unliked ? 1 : 0;
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

    public String getComment() {
        return comment;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public int getCommentLikes() {
        return number_likes;
    }

    public int getCommentUnlikes() {
        return number_unlikes;
    }

    public int getCommentId() {
        return comment_id;
    }

    public int getPostId() {
        return post_id;
    }

    public int getType() {
        return type;
    }

    public boolean getAccountVerified() {
        return account_verified == 1;
    }

    public boolean getLiked() {
        return liked == 1;
    }

    public void setLiked(boolean new_liked) {
        if (new_liked) this.liked = 1;
        else this.liked = 0;
    }

    public boolean getUnliked() {
        return unliked == 1;
    }

    public void setUnliked(boolean new_unliked) {
        if (new_unliked) this.unliked = 1;
        else this.unliked = 0;
    }

    public void setLikes(int new_no_likes) {
        this.number_likes = new_no_likes;
    }

    public void setUnlikes(int new_no_unlikes) {
        this.number_unlikes = new_no_unlikes;
    }

}