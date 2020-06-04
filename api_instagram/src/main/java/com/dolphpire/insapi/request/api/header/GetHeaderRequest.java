package com.dolphpire.insapi.request.api.header;

import android.util.Log;

import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.manager.IGConfig;
import com.dolphpire.insapi.manager.utils.IGUtils;
import com.dolphpire.insapi.request.InsBaseGetRequest;
import com.dolphpire.insapi.response.InsBaseResponseData;
import com.joy.libok.OkHttpManager;

import java.util.Map;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class GetHeaderRequest extends InsBaseGetRequest<InsBaseResponseData> {

  public static final String CSRFTOKEN = "csrftoken";

  @Override
  protected String getActionUrl() {
    return String.format(IGConfig.ACTION_GET_HEADER, IGUtils.generateUuid(false));
  }


  public String getCsrfCookie() {
    HttpUrl url = HttpUrl.parse(getRequestUrl());
    for (Cookie cookie : OkHttpManager.getInstance().getOkHttpClient().cookieJar()
        .loadForRequest(url)) {
      Log.d("GETCOOKIE", "Name: " + cookie.name());
      if (cookie.name().equalsIgnoreCase(CSRFTOKEN)) {
        String value = cookie.value();
        Log.d(TAG, String.format(CSRFTOKEN + "=%s", value));
        IGCommonFieldsManager.getInstance().saveCsrftoken(value);
        return value;
      }
    }
    return "";

  }

  @Override
  protected Map<String, String> getMapParams() {
    return null;
  }
}
