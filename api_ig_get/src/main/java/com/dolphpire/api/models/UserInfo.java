package com.dolphpire.api.models;

public class UserInfo {

    private int user_id = 0;
    private int account_online = 0;
    private int account_private = 0;
    private int account_closed = 0;
    private int account_verified = 0;
    private int verified_email = 0;
    private int verified_phone = 0;
    private int notify_live_video = 0;
    private int notify_message = 0;
    private int notify_message_request = 0;
    private int notify_new_login = 0;
    private int notify_post_comment = 0;
    private int notify_post_comment_interaction = 0;
    private int notify_post_interaction = 0;
    private int notify_post_mention = 0;
    private int notify_post_of_you = 0;
    private int notify_request_accepted = 0;
    private int notify_new_follower = 0;
    private int notify_bio_mention = 0;
    private int notify_product_announcements = 0;
    private int notify_support_request = 0;

    private String username = "";
    private String name = "";
    private String image = "";
    private String log_key = "";

    public void setLogKey(String log_key) {
        this.log_key = log_key;
    }

    public String getLogKey() {
        return log_key;
    }

    public UserInfo() {

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

}