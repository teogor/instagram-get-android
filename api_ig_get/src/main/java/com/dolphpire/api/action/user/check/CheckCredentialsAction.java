package com.dolphpire.api.action.user.check;

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

public class CheckCredentialsAction
{

    //class model
    private String credential;
    private OnFoundCallback.OnFound onFound;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    CheckCredentialsAction(String credential)
    {
        this.credential = String.valueOf(credential);
    }

    public void execute()
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.CHECK_CREDENTIALS, new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {
//                Log.e(TAG, "response: " + response);
                try
                {
                    JSONObject responseObj = new JSONObject(response);
                    boolean error = !responseObj.has("error") || responseObj.getBoolean("error");
                    boolean onSuccess = responseObj.has("onSuccess") && responseObj.getBoolean("onSuccess");
                    int errorID = responseObj.has("errorID") ? responseObj.getInt("errorID") : 0;
                    String errorContent = responseObj.has("errorContent") ? responseObj.getString("errorContent") : "";

                    // check for error flag
                    if (onSuccess)
                    {
                        if (onFound != null)
                        {
                            onFound.onFound(false);
                        }
                    } else if (error && errorID == 178)
                    {
                        if (onFound != null)
                        {
                            onFound.onFound(true);
                        }
                    } else if (error && errorID == 108)
                    {
                        if (onFound != null)
                        {
                            onFound.onFound(true);
                        }
                    } else
                    {
                        if (onFailureListener != null)
                        {
                            Exception exception = new Exception(errorID + ", " + errorContent);
                            onFailureListener.onFailure(exception);
                        }
                    }

                } catch (JSONException ignored)
                {
                    //empty method
                }
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
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUser() == null ? 0 : DolphPireApp.getInstance().getUserID()));

                params.put("credential", credential.equals("0") ? "null" : credential);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public CheckCredentialsAction addOnFoundListener(OnFoundCallback.OnFound onFound)
    {
        this.onFound = onFound;
        return this;
    }

    public CheckCredentialsAction addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public CheckCredentialsAction addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
