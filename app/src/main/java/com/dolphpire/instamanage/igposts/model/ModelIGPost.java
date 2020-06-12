package com.dolphpire.instamanage.igposts.model;

import com.dolphpire.api.models.IGAccountModel;

public class ModelIGPost
{

    private int type;
    private IGAccountModel igAccountModel;
    public ModelIGPost(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setIGAccount(IGAccountModel igAccountModel)
    {
        this.igAccountModel = igAccountModel;
    }

    public IGAccountModel getIgAccountModel()
    {
        return igAccountModel;
    }
}
