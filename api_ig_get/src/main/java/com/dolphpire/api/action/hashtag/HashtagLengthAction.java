package com.dolphpire.api.action.hashtag;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowApiCallback;
import com.dolphpire.api.interfaces.ZFlowFailureCallback;
import com.dolphpire.api.interfaces.ZFlowHashtagSizeCallback;
import com.dolphpire.api.links.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HashtagLengthAction {

    //class model

    private String hashtag;
    private ZFlowHashtagSizeCallback.OnCompleteListener onCompleteListener;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    public HashtagLengthAction(String hashtag) {
        this.hashtag = String.valueOf(hashtag);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.HASHTAG_SIZE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (onCompleteListener != null) {
                            onCompleteListener.onComplete(responseObj.getJSONObject("hashtagData").getInt("count"));
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

                params.put("hashtag", hashtag);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public HashtagLengthAction addOnCompleteListener(ZFlowHashtagSizeCallback.OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public HashtagLengthAction addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public HashtagLengthAction addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}