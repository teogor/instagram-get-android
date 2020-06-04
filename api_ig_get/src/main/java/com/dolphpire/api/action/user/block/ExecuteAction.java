package com.dolphpire.api.action.user.block;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowApiCallback;
import com.dolphpire.api.interfaces.ZFlowFailureCallback;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.interfaces.ZFlowBlockCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ExecuteAction {

    //class model
    private String user_id;
    private String action;
    private ZFlowBlockCallback.BlockUser mBlockUser;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    ExecuteAction(int user_id, int action) {
        this.user_id = String.valueOf(user_id);
        this.action = String.valueOf(action);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_BLOCK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {

                        if (mBlockUser != null) {
                            mBlockUser.onCompleted();
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

                params.put("user_id", user_id);
                params.put("action", action);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public ExecuteAction addOnCompleteListener(ZFlowBlockCallback.BlockUser mBlockUser) {
        this.mBlockUser = mBlockUser;
        return this;
    }

    public ExecuteAction addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public ExecuteAction addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
