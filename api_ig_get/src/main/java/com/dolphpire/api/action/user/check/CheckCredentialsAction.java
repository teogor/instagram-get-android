package com.dolphpire.api.action.user.check;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.interfaces.OnFoundCallback;
import com.dolphpire.api.links.EndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dolphpire.api.initializer.DolphPireApp.TAG;

public class CheckCredentialsAction {

    //class model
    private String credential;
    private OnFoundCallback.OnFound onFound;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    CheckCredentialsAction(String credential) {
        this.credential = String.valueOf(credential);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.CHECK_CREDENTIALS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (onFound != null) {
                            onFound.onFound(false);
                        }

                    } else {
                        JSONObject errorData = responseObj.getJSONObject("errorData");
                        if (errorData.getInt("errorType") == 100) {
                            if (mApiKeyError != null) {
                                mApiKeyError.badApi();
                            }
                        } else if (errorData.getInt("errorType") == 109) {
                            if (onFound != null) {
                                onFound.onFound(true);
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
                params.put("secret_key", DolphPireApp.getInstance().getPackage());
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUser() == null ? 0 : DolphPireApp.getInstance().getUserID()));

                params.put("credential", !credential.equals("0") ? "null" : credential);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public CheckCredentialsAction addOnFoundListener(OnFoundCallback.OnFound onFound) {
        this.onFound = onFound;
        return this;
    }

    public CheckCredentialsAction addOnFailureListener(FailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public CheckCredentialsAction addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
