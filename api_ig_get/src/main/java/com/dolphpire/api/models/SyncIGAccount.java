package com.dolphpire.api.models;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class SyncIGAccount implements Serializable
{

    private IGAccountModel mIGAccountModel;
    private Map<String, SyncListener> INSTANCES = new ArrayMap<>();

    public IGAccountModel getUser()
    {
        return this.mIGAccountModel;
    }

    public void setIGAccount(IGAccountModel user)
    {
        ArrayList<SyncListener> data = new ArrayList<>(INSTANCES.values());
        for (int i = 0; i < data.size(); i++)
        {
            data.get(i).onChange(user);
        }
        this.mIGAccountModel = user;
    }

    public void removeInstance(String TAG)
    {
        this.INSTANCES.remove(TAG);
    }

    public void setListener(SyncListener listener, String TAG)
    {
        this.INSTANCES.put(String.valueOf(TAG), listener);
    }

    public interface SyncListener
    {
        void onChange(IGAccountModel mIGAccount);
    }

}