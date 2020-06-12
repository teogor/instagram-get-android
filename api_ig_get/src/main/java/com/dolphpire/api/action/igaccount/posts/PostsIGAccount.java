package com.dolphpire.api.action.igaccount.posts;

import android.util.Log;

import com.android.volley.Request;
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

public class PostsIGAccount
{

    //class model
    private String ig_userid = "null";
    private ZFlowOnCompleteCallback.OnComplete onComplete;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    PostsIGAccount()
    {

    }

    void setUserID(String ig_userid)
    {
        this.ig_userid = ig_userid;
    }

    public void execute()
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.LINK_IG_POSTS_DETAILS, response ->
        {
            Log.e(TAG, "response: " + response);
            try
            {
                JSONObject responseObj = new JSONObject(response);
                // check for error flag
                if (!responseObj.getBoolean("error"))
                {
                    Log.d("data", "nonnull");
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
        }, error ->
        {

        })
        {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", DolphPireApp.getInstance().getApiKey());
                params.put("secret_key", DolphPireApp.getInstance().getSecretKey());
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUUID()));

                params.put("ig_userid", ig_userid);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public PostsIGAccount addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onComplete)
    {
        this.onComplete = onComplete;
        return this;
    }

    public PostsIGAccount addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public PostsIGAccount addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
