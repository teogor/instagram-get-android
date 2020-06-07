package com.dolphpire.api.models;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ZFlowSyncUser implements Serializable {

    private UserModel mUserModel;
    private Map<String, ChangeListener> INSTANCES = new ArrayMap<>();

    public UserModel getUser() {
        return this.mUserModel;
    }

    public void setUser(UserModel user) {
        ArrayList<ChangeListener> data = new ArrayList<>(INSTANCES.values());
        for (int i = 0; i < data.size(); i++) {
            data.get(i).onChange(user);
        }
        this.mUserModel = user;
    }

    public void removeInstance(String TAG) {
        this.INSTANCES.remove(TAG);
    }

    public void setSyncListener(ChangeListener listener, String TAG) {
        this.INSTANCES.put(String.valueOf(TAG), listener);
    }

    public interface ChangeListener {
        void onChange(UserModel user);
    }

}