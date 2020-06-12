package com.dolphpire.api.models;

import java.io.Serializable;

public class IGPostModel implements Serializable
{

    private boolean is_video;
    private int id;
    private int likes;
    private String img150x150;
    private String img240x240;
    private String img480x480;
    private String img640x640;

    public IGPostModel()
    {

    }

    public int getId()
    {
        return id;
    }

    public int getLikes()
    {
        return likes;
    }

    public String getImg150x150()
    {
        return img150x150;
    }

    public String getImg240x240()
    {
        return img240x240;
    }

    public String getImg480x480()
    {
        return img480x480;
    }

    public String getImg640x640()
    {
        return img640x640;
    }

    public boolean isVideo()
    {
        return is_video;
    }
}