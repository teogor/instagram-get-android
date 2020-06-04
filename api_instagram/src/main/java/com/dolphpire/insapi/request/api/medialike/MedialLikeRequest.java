package com.dolphpire.insapi.request.api.medialike;


import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.manager.IGConfig;
import com.dolphpire.insapi.manager.utils.IGUtils;
import com.dolphpire.insapi.request.InsBasePostRequest;
import com.dolphpire.insapi.response.InsBaseResponseData;

public class MedialLikeRequest extends InsBasePostRequest<MediaLikePayload, InsBaseResponseData> {

  private String mediaId;

  public MedialLikeRequest(String mediaId) {
    this.mediaId = mediaId;
  }

  @Override
  protected String getActionUrl() {
    return String.format(IGConfig.ACTION_GET_MEDIA_LIKE, mediaId);
  }

  @Override
  protected MediaLikePayload getRequestData() {
    String csrftoken = IGCommonFieldsManager.getInstance().getCsrftoken();
    String pkid = IGCommonFieldsManager.getInstance().getPKID();

    MediaLikePayload mediaLikePayload = new MediaLikePayload();
    mediaLikePayload.set_csrftoken(csrftoken);
    mediaLikePayload.set_uid(pkid);
    mediaLikePayload.set_uuid(IGUtils.generateUuid(true));
    mediaLikePayload.setRadio_type("wifi-none");
    mediaLikePayload.setMedia_id(mediaId);
    return mediaLikePayload;
  }
}
