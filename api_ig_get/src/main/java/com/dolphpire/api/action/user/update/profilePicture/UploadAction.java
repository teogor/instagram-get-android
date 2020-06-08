package com.dolphpire.api.action.user.update.profilePicture;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.interfaces.ZFlowOnCompleteCallback;
import com.dolphpire.api.links.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UploadAction {

    //class model
    private String image_id;
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    UploadAction(String image_id) {
        this.image_id = String.valueOf(image_id);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_PROFILE_PICTURES_UPLOAD, new Response.Listener<String>() {

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
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUUID()));

                params.put("image_id", image_id.equals("1") ? "NULL" : image_id);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UploadAction addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public UploadAction addOnFailureListener(FailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UploadAction addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
