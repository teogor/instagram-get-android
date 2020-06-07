package com.dolphpire.api.models;

import java.io.Serializable;

public class UserModel implements Serializable
{

    private int user_id = 0;

    private String email = "";
    private String username = "";
    private String password = "";

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

}