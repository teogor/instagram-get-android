package com.dolphpire.insapi.request;

import com.dolphpire.insapi.response.InsBaseResponseData;

public interface InsRequestCallBack<R extends InsBaseResponseData> {

  void onSuccess(int statusCode, R insBaseData);

  void onFailure(int errorCode, String errorMsg);
}
