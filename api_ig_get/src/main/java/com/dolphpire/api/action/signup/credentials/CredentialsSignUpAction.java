package com.dolphpire.api.action.signup.credentials;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.ZFlowCredentialsCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.links.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CredentialsSignUpAction {

    //class model

    private String username;
    private String email;
    private ZFlowCredentialsCallback.OnCredentialsCompleted onCompleteListener;
    private ZFlowCredentialsCallback.OnCredentialsError onCredentialsError;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    CredentialsSignUpAction() {

    }

    void setUsername(String username) {
        this.username = username;
    }

    void setEmail(String email) {
        this.email = email;
    }


    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.SIGNUP_CREDENTIALS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    int new_uid = responseObj.getJSONObject("signUpCredentials").getInt("insert_id");
                    if (!responseObj.getBoolean("error") && new_uid != 0) {
                        if (DolphPireApp.getInstance().getNewUID() == 0) {
                            DolphPireApp.getInstance().setNewUID(new_uid);
                            if (onCompleteListener != null) {
                                onCompleteListener.onInsert();
                            }
                        } else {
                            if (onCompleteListener != null) {
                                onCompleteListener.onUpdate();
                            }
                        }
                    } else {
                        JSONObject errorData = responseObj.getJSONObject("errorData");
                        if (errorData.getInt("errorType") == 100) {
                            if (mApiKeyError != null) {
                                mApiKeyError.badApi();
                            }
                        } else if (errorData.getInt("errorType") == 115) {
                            if (onCredentialsError != null) {
                                onCredentialsError.onInsertError();
                            }
                        } else if (errorData.getInt("errorType") == 116) {
                            if (onCredentialsError != null) {
                                onCredentialsError.onUpdateError();
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

                params.put("username", username.equals("0") ? "0" : username);
                params.put("email", email.equals("0") ? "0" : email);
                params.put("user_id", String.valueOf(DolphPireApp.getInstance().getNewUID() == 0 ? "0" : DolphPireApp.getInstance().getNewUID()));
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public CredentialsSignUpAction addOnCompleteListener(ZFlowCredentialsCallback.OnCredentialsCompleted onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public CredentialsSignUpAction addOnFailureListener(ZFlowCredentialsCallback.OnCredentialsError onCredentialsError) {
        this.onCredentialsError = onCredentialsError;
        return this;
    }

    public CredentialsSignUpAction addOnFailureListener(FailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public CredentialsSignUpAction addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
