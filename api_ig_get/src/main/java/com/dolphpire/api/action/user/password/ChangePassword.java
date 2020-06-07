package com.dolphpire.api.action.user.password;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowPasswordCallback;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword {

    //class model

    private String password;
    private String newPassword;
    private ZFlowPasswordCallback.OnChangePassword onChangePassword;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    ChangePassword() {

    }

    void setOldPassword(String password) {
        this.password = String.valueOf(password);
    }

    void setNewPassword(String newPassword) {
        this.newPassword = String.valueOf(newPassword);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_PASSWORD_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (onChangePassword != null) {
                            onChangePassword.onChanged();
                        }
                    } else {
                        JSONObject errorData = responseObj.getJSONObject("errorData");
                        if (errorData.getInt("errorType") == 100) {
                            if (mApiKeyError != null) {
                                mApiKeyError.badApi();
                            }
                        } else if (errorData.getInt("errorType") == 124) {
                            if (onChangePassword != null) {
                                onChangePassword.onWrongPassword();
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
//                params.put("log_key", String.valueOf(DolphPireApp.getInstance().getUser().getLogKey()));

                params.put("newPassword", newPassword);
                params.put("password", password);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public ChangePassword addOnChangePasswordListener(ZFlowPasswordCallback.OnChangePassword onChangePassword) {
        this.onChangePassword = onChangePassword;
        return this;
    }

    public ChangePassword addOnFailureListener(FailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public ChangePassword addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
