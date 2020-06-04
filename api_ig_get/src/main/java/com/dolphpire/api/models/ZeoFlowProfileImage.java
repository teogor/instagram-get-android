package com.dolphpire.api.models;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZeoFlowProfileImage {

    private int user_id = 0;
    private int deleted = 0;
    private int type = 0;

    private String image_id = "";

    private boolean selected = false;
    private ChangeListener listener;

    private String last_time_applied;
    private String created_at;

    public ZeoFlowProfileImage(int typeN) {
        this.type = typeN;
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public Timestamp getLastTimeApplied() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(last_time_applied);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timestamp getCreatedAt() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(created_at);
            assert date != null;
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getIsDeleted() {
        return deleted == 1;
    }

    public int getType() {
        return type;
    }

    public int getUserId() {
        return user_id;
    }

    public String getImageId() {
        return image_id;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (listener != null) listener.onChange();
    }

    public interface ChangeListener {
        void onChange();
    }

}
