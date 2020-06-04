package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ZeoFlowUser implements Serializable {

    private int user_id = 0;
    private int authentication_type = 0;
    private int account_online = 0;
    private int account_private = 0;
    private int account_closed = 0;
    private int account_verified = 0;
    private int business_account = 0;
    private int verified_email = 0;
    private int verified_phone = 0;
    private int message_privacy = 0;

    private int show_birthday = 0;
    private int show_business_address = 0;
    private int show_business_email = 0;
    private int show_business_phone = 0;
    private int show_business_hq = 0;
    private int show_country = 0;
    private int show_email = 0;
    private int show_founded_date = 0;
    private int show_gender = 0;
    private int show_join_date = 0;
    private int show_phone = 0;
    private int show_user_online = 0;
    private int show_website = 0;

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

    private int no_posts = 0;
    private int followers = 0;
    private int following = 0;
    private int is_requested = 0;
    private int is_following = 0;
    private int is_blocked = 0;
    private int mutual_followers = 0;

    private String email = "";
    private String gender = "";
    private String username = "";
    private String phone = "";
    private String name = "";
    private String country_code = "";
    private String image = "";
    private String bio = "";
    private String status = "";
    private String website = "";
    private String gradient_colour_1 = "";
    private String gradient_colour_2 = "";
    private String business_address = "";
    private String business_categories = "";
    private String business_email = "";
    private String business_location = "";
    private String business_phone = "";
    private String business_postal_code = "";
    private String trusted_devices = "";
    private String log_key = "";

    public void setLogKey(String log_key) {
        this.log_key = log_key;
    }

    public String getLogKey() {
        return log_key;
    }

    private ArrayList<AboutFeaturedProfileModel> featured_profiles = new ArrayList<>();
    private ArrayList<ZeoFlowMutualInfo> mutual_followers_data = new ArrayList<>();
    private ArrayList<ZeoFlowDiscoverModel> search_history = new ArrayList<>();

    private Date date_of_birthday;
    private Date business_founded_date;

    private String last_time_online;
    private String joining_date;

    public ZeoFlowUser() {

    }

    public Date getDateOfBirthday() {
        return date_of_birthday;
    }

    public Date getBusinessFounded() {
        return business_founded_date;
    }

    public Timestamp getLastTimeOnline() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(last_time_online);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timestamp getJoiningDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(joining_date);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNoPosts() {
        return no_posts;
    }

    public int getNoFollowers() {
        return followers;
    }

    public void setNoFollowers(int followers) {
        this.followers = followers;
    }

    public int getNoFollowing() {
        return following;
    }

    public int getUserId() {
        return user_id;
    }

    public int getMutualFollowers() {
        return mutual_followers;
    }

    public String getUsername() {
        return username;
    }

    public String getCountry() {
        return country_code;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getBio() {
        return bio;
    }

    public String getBusinessLocation() {
        return business_location;
    }

    public String getBusinessPostalCode() {
        return business_postal_code;
    }

    public String getBusinessAddress() {
        return business_address;
    }

    public String getBusinessEmail() {
        return business_email;
    }

    public String getBusinessPhone() {
        return business_phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getBusinessCategories() {
        List<String> mDataImagesList;
        String[] array = business_categories.split(",");
        mDataImagesList = Arrays.asList(array);
        return mDataImagesList;
    }

    public List<AboutFeaturedProfileModel> getFeaturedProfiles() {
        return featured_profiles;
    }

    public List<ZeoFlowMutualInfo> getMutualFollowersData() {
        return mutual_followers_data;
    }

    public List<ZeoFlowDiscoverModel> getSearchHistory() {
        return search_history;
    }

    public String getColor1() {
        return "#" + gradient_colour_1;
    }

    public String getColor2() {
        return "#" + gradient_colour_2;
    }

    public boolean getAccountVerified() {
        return account_verified == 1;
    }

    public boolean getAccountClosed() {
        return account_closed == 1;
    }

    public boolean getAccountOnline() {
        return account_online == 1;
    }

    public boolean getAccountPrivate() {
        return account_private == 1;
    }

    public boolean getBusinessAccount() {
        return business_account == 1;
    }

    public boolean getShowUserOnline() {
        return show_user_online == 1;
    }

    public boolean getShowBirthday() {
        return show_birthday == 1;
    }

    public boolean getShowBusinessAddress() {
        return show_business_address == 1;
    }

    public boolean getShowBusinessEmail() {
        return show_business_email == 1;
    }

    public boolean getShowBusinessPhone() {
        return show_business_phone == 1;
    }

    public boolean getShowBusinessHQ() {
        return show_business_hq == 1;
    }

    public boolean getShowCountry() {
        return show_country == 1;
    }

    public boolean getShowEmail() {
        return show_email == 1;
    }

    public boolean getShowFoundDate() {
        return show_founded_date == 1;
    }

    public boolean getShowGender() {
        return show_gender == 1;
    }

    public boolean getShowJoinDate() {
        return show_join_date == 1;
    }

    public boolean getShowPhone() {
        return show_phone == 1;
    }

    public boolean getShowWebsite() {
        return show_website == 1;
    }

    public boolean getIsFollowing() {
        return is_following == 1;
    }

    public boolean getIsRequested() {
        return is_requested == 1;
    }

    public boolean getIsBlocked() {
        return is_blocked == 1;
    }

    public void setFollowing(boolean newMode) {
        if (newMode) this.is_following = 1;
        else this.is_following = 0;
    }

    public void setRequested(boolean newMode) {
        if (newMode) this.is_requested = 2;
        else this.is_requested = 0;
    }

    public void setBlocked(boolean newMode) {
        if (newMode) this.is_blocked = 1;
        else this.is_blocked = 0;
    }

}