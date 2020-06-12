package com.dolphpire.api.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable
{

    private int user_id = 0;

    private String email = "";
    private String username = "";
    private String password = "";
    private ArrayList<IGAccountModel> ig_accounts = new ArrayList<>();
    private IGPostModel mIGPostModel;

    private int coins = 0;

    public UserModel()
    {

    }

    public int getCoins()
    {
        return coins;
    }

    public int getUUID()
    {
        return user_id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public ArrayList<IGAccountModel> getIGAccounts()
    {
        return ig_accounts;
    }

    public void setIGPostModel(IGPostModel mIGPostModel)
    {
        this.mIGPostModel = mIGPostModel;
    }

    public IGPostModel getIGPostModel()
    {
        return mIGPostModel;
    }

}