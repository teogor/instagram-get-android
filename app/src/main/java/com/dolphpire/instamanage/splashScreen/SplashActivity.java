package com.dolphpire.instamanage.splashScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.instamanage.home.HomeActivity;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.joy.libok.OkHttpManager;
import com.joy.libok.configdata.OKConfigData;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initOkManager();
        initInsFiledManger();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    private void initOkManager() {
        OKConfigData okConfigData = new OKConfigData();
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
                new SharedPrefsCookiePersistor(SplashActivity.this));
        okConfigData.setCookiesJar(cookieJar);
        OkHttpManager.getInstance().init(okConfigData);

    }

    private void initInsFiledManger() {
        IGCommonFieldsManager.getInstance().init(this);
    }

}
