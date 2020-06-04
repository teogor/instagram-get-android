package com.dolphpire.api.action.user.authenticate;

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

public class UserAuthLogKey {

    //class model

    private String logKey;
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    UserAuthLogKey(String logKey) {
        this.logKey = logKey;
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_AUTH_LOGKEY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (onComplete != null) {
                            onComplete.onCompleted();
                        }
                    } else {
                        if (onFailureListener != null) {
                            Exception exception = new Exception("Unknown Error");
                            onFailureListener.onFailure(exception);
                        }
                    }

                } catch (JSONException ignored) {
                    //empty method
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (onFailureListener != null) {
                    Exception exception = new Exception(String.valueOf(error.networkResponse));
                    onFailureListener.onFailure(exception);
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", DolphPireApp.getInstance().getApiKey());
                params.put("package_name", DolphPireApp.getInstance().getPackage());
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUserID()));
                params.put("log_key", logKey.equals("") ? "NULL" : logKey);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UserAuthLogKey addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public UserAuthLogKey addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UserAuthLogKey addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
