package com.dolphpire.api.action.user.verified.send;

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

public class VerifiedSendRequest {

    private String knownFor = "";
    private String documentID = "";
    private String category = "";
    private String fullName = "";
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    VerifiedSendRequest() {

    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_VERIFIED_SEND_REQUEST, new Response.Listener<String>() {

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

                params.put("fullName", fullName.equals("") ? "NULL" : fullName);
                params.put("category", category.equals("") ? "NULL" : category);
                params.put("documentID", documentID.equals("") ? "NULL" : documentID);
                params.put("knownFor", knownFor.equals("") ? "NULL" : knownFor);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public VerifiedSendRequest addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public VerifiedSendRequest addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public VerifiedSendRequest addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

    void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    void setCategory(int category) {
        this.category = String.valueOf(category);
    }

    void setKnownFor(String knownFor) {
        this.knownFor = knownFor;
    }

    void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
