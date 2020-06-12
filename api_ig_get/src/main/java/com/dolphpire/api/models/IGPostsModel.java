package com.dolphpire.api.models;

import java.io.Serializable;
import java.util.ArrayList;

public class IGPostsModel implements Serializable
{

    private boolean has_next_page;
    private String end_cursor = "";
    private ArrayList<IGPostModel> posts = new ArrayList<>();

    public IGPostsModel()
    {

    }

    public ArrayList<IGPostModel> getPosts()
    {
        return posts;
    }

    public boolean hasNextPage()
    {
        return has_next_page;
    }

    public String getEndCursor()
    {
        return end_cursor;
    }
}