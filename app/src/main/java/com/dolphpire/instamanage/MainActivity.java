package com.dolphpire.instamanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.request.InsRequestCallBack;
import com.dolphpire.insapi.request.api.header.GetHeaderRequest;
import com.dolphpire.insapi.request.api.login.LoginRequest;
import com.dolphpire.insapi.request.api.login.LoginResponseData;
import com.dolphpire.insapi.response.InsBaseResponseData;
import com.joy.libok.OkHttpManager;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.test.log.LLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void getCsftoken() {
        final GetHeaderRequest getHeaderRequest = new GetHeaderRequest();
        getHeaderRequest.execute(new InsRequestCallBack() {
            @Override
            public void onSuccess(int statusCode, InsBaseResponseData insBaseData) {
                String csrfCookie = getHeaderRequest.getCsrfCookie();
                Log.d("succeed", "get Csftoken onSuccess，csrfCookie =  " + csrfCookie);
            }

            @Override
            public void onFailure(int errorCode, String errorMsg) {
                Log.d("error", "get Csftoken onFailure ");
            }
        });

    }

    private void login() {

//        EditText userName = findViewById(R.id.editTextTextPersonName);
//        EditText pwd = findViewById(R.id.editTextTextPassword);
        getCsftoken();

//        LoginRequest loginRequest = new LoginRequest(userName.getText().toString(), pwd.getText().toString());
//        loginRequest.execute(new InsRequestCallBack<LoginResponseData>() {
//            @Override
//            public void onSuccess(int statusCode, LoginResponseData insBaseData) {
//                LoginResponseData.LoggedInUserBean loggedInUserBean = null;
//                if (insBaseData != null && (loggedInUserBean = insBaseData.getLogged_in_user()) != null) {
//                    String pkId = loggedInUserBean.getPk() + "";
//                    if (!TextUtils.isEmpty(pkId)) {
//                        IGCommonFieldsManager.getInstance().savePKID(pkId);
//                    }
//                    Log.d("login", "pkId = " + pkId);
//                }
//            }
//
//            @Override
//            public void onFailure(int errorCode, String errorMsg) {
//                LLog.d("failed", String.format("errorCode= %s , errorMsg = %s", errorCode, errorMsg));
//            }
//        });

    }
}