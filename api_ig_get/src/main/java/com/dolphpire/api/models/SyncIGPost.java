package com.dolphpire.api.models;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class SyncIGPost implements Serializable
{

    private IGPostModel mIGPostModel;
    private Map<String, SyncListener> INSTANCES = new ArrayMap<>();

    public IGPostModel getPost()
    {
        return this.mIGPostModel;
    }

    public void setNewPost(IGPostModel mIGPostModel)
    {
        ArrayList<SyncListener> data = new ArrayList<>(INSTANCES.values());
        for (int i = 0; i < data.size(); i++)
        {
            data.get(i).onChange(mIGPostModel);
        }
        this.mIGPostModel = mIGPostModel;
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
        void onChange(IGPostModel mIGPostModel);
    }

}