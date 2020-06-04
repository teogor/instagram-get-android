package com.dolphpire.api.action.user.update.credentials;

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

public class UpdateCredentials {

    //class model

    private String username = "";
    private String email = "";
    private String phone = "";
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    UpdateCredentials() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_CREDENTIALS_UPDATE, new Response.Listener<String>() {

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

                params.put("username", username.equals("") ? "NULL" : username);
                params.put("email", email.equals("") ? "NULL" : email);
                params.put("phone", phone.equals("") ? "NULL" : phone);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UpdateCredentials addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public UpdateCredentials addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UpdateCredentials addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
