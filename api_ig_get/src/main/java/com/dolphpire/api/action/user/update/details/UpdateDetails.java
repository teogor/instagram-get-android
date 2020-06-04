package com.dolphpire.api.action.user.update.details;

import android.util.Log;

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

import static com.dolphpire.api.initializer.DolphPireApp.TAG;

public class UpdateDetails {

    //class model

    private String name = "NULL";
    private String country = "NULL";
    private String gender = "NULL";
    private String birthday = "NULL";
    private String website = "NULL";
    private String status = "NULL";
    private String bio = "NULL";
    private String businessEmail = "NULL";
    private String businessPhone = "NULL";
    private String businessAddress = "NULL";
    private String businessPostalCode = "NULL";
    private String businessLocation = "NULL";
    private String businessFounded = "NULL";
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    UpdateDetails() {

    }

    void setName(String name) {
        this.name = name;
    }

    void setCountry(String country) {
        this.country = country;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    void setWebsite(String website) {
        this.website = website;
    }

    void setStatus(String status) {
        this.status = status;
    }

    void setBio(String bio) {
        this.bio = bio;
    }

    void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    void setBusinessPostalCode(String businessPostalCode) {
        this.businessPostalCode = businessPostalCode;
    }

    void setBusinessLocation(String businessLocation) {
        this.businessLocation = businessLocation;
    }

    void setBusinessFounded(String businessFounded) {
        this.businessFounded = businessFounded;
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_DETAILS_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);
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

                params.put("name", name);
                params.put("country", country);
                params.put("gender", gender);
                params.put("birthday", birthday);
                params.put("website", website);
                params.put("status", status);
                params.put("bio", bio);
                params.put("businessAddress", businessAddress);
                params.put("businessEmail", businessEmail);
                params.put("businessFounded", businessFounded);
                params.put("businessLocation", businessLocation);
                params.put("businessPhone", businessPhone);
                params.put("businessPostalCode", businessPostalCode);
                Log.d("params", params.toString());
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UpdateDetails addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public UpdateDetails addOnFailureListener(FailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UpdateDetails addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
