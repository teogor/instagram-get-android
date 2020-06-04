package com.dolphpire.insapi.request.api.followingcancel;

import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.manager.IGConfig;
import com.dolphpire.insapi.manager.utils.IGUtils;
import com.dolphpire.insapi.request.InsBasePostRequest;

public class FollowingCancelRequest extends
    InsBasePostRequest<FollowingCancelPayload, FollowingCancelResponseData> {

  private String userId;

  public FollowingCancelRequest(String userId) {
    this.userId = userId;
  }

  @Override
  protected String getActionUrl() {
    return String.format(IGConfig.ACTION_GET_FOLLOWING_CANCEL, userId);
  }

  @Override
  protected FollowingCancelPayload getRequestData() {

    String csrftoken = IGCommonFieldsManager.getInstance().getCsrftoken();
    String pkid = IGCommonFieldsManager.getInstance().getPKID();

    FollowingCancelPayload followingCreatePayload = new FollowingCancelPayload();
    followingCreatePayload.set_csrftoken(csrftoken);
    followingCreatePayload.set_uid(pkid);
    followingCreatePayload.set_uuid(IGUtils.generateUuid(true));
    followingCreatePayload.setUser_id(userId);
    followingCreatePayload.setRadio_type("wifi-none");

    return followingCreatePayload;
  }
}
