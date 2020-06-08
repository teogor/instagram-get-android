package com.dolphpire.instamanage.application;

import android.app.Application;

import androidx.annotation.NonNull;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowUserCallback;
import com.dolphpire.api.models.UserModel;

public class App extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (DolphPireApp.getInstance().getUser() != null)
        {
            DolphPireApp.initializeApi()
                    .user()
                    .details()
                    .withUUID(DolphPireApp.getInstance().getUUID())
                    .execute();
        }

    }

}
