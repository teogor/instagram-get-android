package com.dolphpire.api.action.igaccount.addAccount;

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

public class LinkIGAccount
{

    //class model

    private String igid = "null";
    private String username = "null";
    private String password = "null";
    private String profile_picture = "null";
    private String is_private = "null";
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    LinkIGAccount()
    {

    }

    void setIGID(String igid)
    {
        this.igid = igid;
    }

    void setUsername(String username)
    {
        this.username = username;
    }

    void setPassword(String password)
    {
        this.password = password;
    }

    void setProfilePicture(String profile_picture)
    {
        this.profile_picture = profile_picture;
    }

    void setIsPrivate(String is_private)
    {
        this.is_private = is_private;
    }

    public void execute()
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_DETAILS_UPDATE, response ->
        {
            Log.e(TAG, "response: " + response);
            try
            {
                JSONObject responseObj = new JSONObject(response);
                // check for error flag
                if (!responseObj.getBoolean("error"))
                {
                    if (onComplete != null)
                    {
                        onComplete.onCompleted();
                    }
                } else
                {
                    JSONObject errorData = responseObj.getJSONObject("errorData");
                    if (errorData.getInt("errorID") == 511)
                    {
                        if (mApiKeyError != null)
                        {
                            mApiKeyError.badApi();
                        }
                    } else
                    {
                        if (onFailureListener != null)
                        {
                            Exception exception = new Exception(errorData.getInt("errorType") + ", " + errorData.getInt("errorMessage"));
                            onFailureListener.onFailure(exception);
                        }
                    }
                }

            } catch (JSONException ignored)
            {
                //empty method
            }
        }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        })
        {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", DolphPireApp.getInstance().getApiKey());
                params.put("secret_key", DolphPireApp.getInstance().getSecretKey());
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUUID()));

                params.put("username", username);
                params.put("igid", igid);
                params.put("password", password);
                params.put("profile_picture", profile_picture);
                params.put("is_private", is_private);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public LinkIGAccount addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete)
    {
        this.onComplete = onComplete;
        return this;
    }

    public LinkIGAccount addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public LinkIGAccount addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
