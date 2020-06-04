package com.dolphpire.api.action.user.update.visibility;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowApiCallback;
import com.dolphpire.api.interfaces.ZFlowFailureCallback;
import com.dolphpire.api.interfaces.ZFlowOnCompleteCallback;
import com.dolphpire.api.links.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateVisibility {

    //class model

    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    private int showBirthday = -1;
    private int showBusinessAddress = -1;
    private int showBusinessEmail = -1;
    private int showBusinessPhone = -1;
    private int showBusinessHQ = -1;
    private int showCountry = -1;
    private int showEmail = -1;
    private int showFoundedDate = -1;
    private int showGender = -1;
    private int showJoinedDate = -1;
    private int showPhone = -1;
    private int showUserOnline = -1;
    private int showWebsite = -1;

    UpdateVisibility() {

    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_VISIBILITY_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (onComplete != null) {
                            onComplete.onCompleted();
                        }
                    } else {
                        JSONObject errorData = responseObj.getJSONObject("errorData");
                        if (errorData.getInt("errorType") == 100) {
                            if (mApiKeyError != null) {
                                mApiKeyError.badApi();
                            }
                        } else {
                            if (onFailureListener != null) {
                                Exception exception = new Exception(errorData.getInt("errorType") + ", " + errorData.getInt("errorMessage"));
                                onFailureListener.onFailure(exception);
                            }
                        }
                    }

                } catch (JSONException ignored) {
                    //empty method
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", DolphPireApp.getInstance().getApiKey());
                params.put("package_name", DolphPireApp.getInstance().getPackage());
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUserID()));

                params.put("showBirthday", String.valueOf(showBirthday));
                params.put("showBusinessAddress", String.valueOf(showBusinessAddress));
                params.put("showBusinessEmail", String.valueOf(showBusinessEmail));
                params.put("showBusinessPhone", String.valueOf(showBusinessPhone));
                params.put("showBusinessHQ", String.valueOf(showBusinessHQ));
                params.put("showCountry", String.valueOf(showCountry));
                params.put("showEmail", String.valueOf(showEmail));
                params.put("showFoundedDate", String.valueOf(showFoundedDate));
                params.put("showGender", String.valueOf(showGender));
                params.put("showJoinedDate", String.valueOf(showJoinedDate));
                params.put("showPhone", String.valueOf(showPhone));
                params.put("showUserOnline", String.valueOf(showUserOnline));
                params.put("showWebsite", String.valueOf(showWebsite));
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UpdateVisibility addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public UpdateVisibility addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UpdateVisibility addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

    void showBirthday(boolean showBirthday) {
        this.showBirthday = showBirthday ? 1 : 0;
    }

    void showBusinessAddress(boolean showBusinessAddress) {
        this.showBusinessAddress = showBusinessAddress ? 1 : 0;
    }

    void showBusinessEmail(boolean showBusinessEmail) {
        this.showBusinessEmail = showBusinessEmail ? 1 : 0;
    }

    void showBusinessPhone(boolean showBusinessPhone) {
        this.showBusinessPhone = showBusinessPhone ? 1 : 0;
    }

    void showBusinessHQ(boolean showBusinessHQ) {
        this.showBusinessHQ = showBusinessHQ ? 1 : 0;
    }

    void showCountry(boolean showCountry) {
        this.showCountry = showCountry ? 1 : 0;
    }

    void showEmail(boolean showEmail) {
        this.showEmail = showEmail ? 1 : 0;
    }

    void showFoundedDate(boolean showFoundedDate) {
        this.showFoundedDate = showFoundedDate ? 1 : 0;
    }

    void showGender(boolean showGender) {
        this.showGender = showGender ? 1 : 0;
    }

    void showJoinedDate(boolean showJoinedDate) {
        this.showJoinedDate = showJoinedDate ? 1 : 0;
    }

    void showPhone(boolean showPhone) {
        this.showPhone = showPhone ? 1 : 0;
    }

    void showUserOnline(boolean showUserOnline) {
        this.showUserOnline = showUserOnline ? 1 : 0;
    }

    void showWebsite(boolean showWebsite) {
        this.showWebsite = showWebsite ? 1 : 0;
    }
}
