package com.dolphpire.insapi.request.api.userinfo;


import com.dolphpire.insapi.manager.IGConfig;
import com.dolphpire.insapi.request.InsBaseGetRequest;

import java.util.Map;

public class GetUserDetails extends InsBaseGetRequest<UserInfoResponseData> {

  private String userName;

  public GetUserDetails(String userName) {
    this.userName = userName;
  }

  @Override
  protected Map<String, String> getMapParams() {
    return null;
  }

  @Override
  protected String getActionUrl() {
    return String.format(IGConfig.ACTION_GET_USER_INFO, userName);
  }
}
