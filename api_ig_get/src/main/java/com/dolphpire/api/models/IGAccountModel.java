package com.dolphpire.api.models;

import java.io.Serializable;
import java.util.ArrayList;

public class IGAccountModel implements Serializable
{

    private long ig_account_id = 0;
    private String profile_picture = "";
    private String username = "";
    private String password = "";
    private IGPostsModel mIGPostsModel;

    public IGAccountModel()
    {

    }

    public void setIg_account_id(int ig_account_id)
    {
        this.ig_account_id = ig_account_id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setProfile_picture(String profile_picture)
    {
        this.profile_picture = profile_picture;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getProfilePicture()
    {
        return profile_picture;
    }

    public long getIGID()
    {
        return ig_account_id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setIGPostsModel(IGPostsModel mIGPostsModel)
    {
        this.mIGPostsModel = mIGPostsModel;
    }

    public IGPostsModel getIGPosts()
    {
        return mIGPostsModel;
    }
}