package com.dolphpire.api.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZeoFlowPost implements Serializable, Parcelable {

    private String username = "";
    private String name = "";
    private String image = "";
    private String description = "";
    private String images_url = "";
    private String color_1 = "";
    private String color_2 = "";

    private int owner_id = 0;
    private int number_likes = 0;
    private int number_unlikes = 0;
    private int number_views = 0;
    private int post_type = 0;
    private int post_id = 0;
    private int account_verified = 0;
    private int liked = 0;
    private int unliked = 0;
    private int saved = 0;
    private int mutual_followers = 0;

    private String date_posted = "";

    private ArrayList<ZeoFlowMutualInfo> mutual_followers_data = new ArrayList<>();

    protected ZeoFlowPost(Parcel in) {
        username = in.readString();
        name = in.readString();
        image = in.readString();
        description = in.readString();
        images_url = in.readString();
        color_1 = in.readString();
        color_2 = in.readString();
        owner_id = in.readInt();
        number_likes = in.readInt();
        number_unlikes = in.readInt();
        number_views = in.readInt();
        post_type = in.readInt();
        post_id = in.readInt();
        account_verified = in.readInt();
        liked = in.readInt();
        unliked = in.readInt();
        saved = in.readInt();
        mutual_followers = in.readInt();
        date_posted = in.readString();
    }

    public static final Creator<ZeoFlowPost> CREATOR = new Creator<ZeoFlowPost>() {
        @Override
        public ZeoFlowPost createFromParcel(Parcel in) {
            return new ZeoFlowPost(in);
        }

        @Override
        public ZeoFlowPost[] newArray(int size) {
            return new ZeoFlowPost[size];
        }
    };

    public int getMutualFollowers() {
        return mutual_followers;
    }

    public List<ZeoFlowMutualInfo> getMutualFollowersData() {
        return mutual_followers_data;
    }

    public Timestamp getDatePosted() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(date_posted);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTimestamp() {
        return date_posted;
    }

    public String getImages() {
        return images_url;
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

    public String getDescription() {
        return description;
    }

    public String getColor1() {
        return "#" + color_1;
    }

    public String getColor2() {
        return "#" + color_2;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public int getPostLikes() {
        return number_likes;
    }

    public int getPostUnlikes() {
        return number_unlikes;
    }

    public int getPostViews() {
        return number_views;
    }

    public int getPostId() {
        return post_id;
    }

    public int getPostType() {
        return post_type;
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

    public boolean getSaved() {
        return saved == 1;
    }

    public void setSaved(boolean new_saved) {
        if (new_saved) this.saved = 1;
        else this.saved = 0;
    }

    public void setLikes(int new_no_likes) {
        this.number_likes = new_no_likes;
    }

    public void setUnlikes(int new_no_unlikes) {
        this.number_unlikes = new_no_unlikes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(images_url);
        dest.writeString(color_1);
        dest.writeString(color_2);
        dest.writeInt(owner_id);
        dest.writeInt(number_likes);
        dest.writeInt(number_unlikes);
        dest.writeInt(number_views);
        dest.writeInt(post_type);
        dest.writeInt(post_id);
        dest.writeInt(account_verified);
        dest.writeInt(liked);
        dest.writeInt(unliked);
        dest.writeInt(saved);
        dest.writeInt(mutual_followers);
        dest.writeString(date_posted);
    }
}