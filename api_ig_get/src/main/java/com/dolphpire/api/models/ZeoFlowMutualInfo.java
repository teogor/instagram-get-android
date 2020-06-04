package com.dolphpire.api.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ZeoFlowMutualInfo implements Parcelable {

    private int user_id = 0;

    private String username = "";
    private String image = "";

    ZeoFlowMutualInfo() {

    }

    protected ZeoFlowMutualInfo(Parcel in) {
        user_id = in.readInt();
        username = in.readString();
        image = in.readString();
    }

    public static final Creator<ZeoFlowMutualInfo> CREATOR = new Creator<ZeoFlowMutualInfo>() {
        @Override
        public ZeoFlowMutualInfo createFromParcel(Parcel in) {
            return new ZeoFlowMutualInfo(in);
        }

        @Override
        public ZeoFlowMutualInfo[] newArray(int size) {
            return new ZeoFlowMutualInfo[size];
        }
    };

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return "@" + username;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(username);
        dest.writeString(image);
    }
}