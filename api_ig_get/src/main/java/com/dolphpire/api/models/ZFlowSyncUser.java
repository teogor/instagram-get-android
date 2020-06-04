package com.dolphpire.api.models;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ZFlowSyncUser implements Serializable {

    private ZeoFlowUser mZeoFlowUser;
    private Map<String, ChangeListener> INSTANCES = new ArrayMap<>();

    public ZeoFlowUser getUser() {
        return this.mZeoFlowUser;
    }

    public void setUser(ZeoFlowUser user) {
        ArrayList<ChangeListener> data = new ArrayList<>(INSTANCES.values());
        for (int i = 0; i < data.size(); i++) {
            data.get(i).onChange(user);
        }
        this.mZeoFlowUser = user;
    }

    public void removeInstance(String TAG) {
        this.INSTANCES.remove(TAG);
    }

    public void setSyncListener(ChangeListener listener, String TAG) {
        this.INSTANCES.put(String.valueOf(TAG), listener);
    }

    public interface ChangeListener {
        void onChange(ZeoFlowUser user);
    }

}