package com.dolphpire.instamanage.application;

import android.app.Application;

import com.dolphpire.api.initializer.DolphPireApp;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DolphPireApp.initializeApi();

    }

}
