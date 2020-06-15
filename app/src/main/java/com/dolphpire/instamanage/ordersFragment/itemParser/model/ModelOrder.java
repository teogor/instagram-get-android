package com.dolphpire.instamanage.ordersFragment.itemParser.model;

import com.dolphpire.api.models.IGAccountModel;

public class ModelOrder
{

    private int type;
    private IGAccountModel igAccountModel;
    public ModelOrder(int type) {
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
