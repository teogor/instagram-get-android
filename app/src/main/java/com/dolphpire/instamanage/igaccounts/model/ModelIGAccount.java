package com.dolphpire.instamanage.igaccounts.model;

import com.dolphpire.api.models.IGAccountModel;

public class ModelIGAccount {

    private int type;
    private IGAccountModel igAccountModel;
    public ModelIGAccount(int type) {
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
