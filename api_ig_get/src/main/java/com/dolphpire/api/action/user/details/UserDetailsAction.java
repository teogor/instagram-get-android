package com.dolphpire.api.action.user.details;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.interfaces.ZFlowUserCallback;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dolphpire.api.initializer.DolphPireApp.TAG;

public class UserDetailsAction
{

    private String uuid;
    private ZFlowUserCallback.OnCompleteListener<UserModel> onCompleteListener;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    UserDetailsAction(int user_id)
    {
        this.uuid = String.valueOf(user_id);
    }

    public void execute()
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_DETAILS, response ->
        {
            Log.e(TAG, "response: " + response);
            try
            {
                JSONObject responseObj = new JSONObject(response);
                // check for error flag
                if (!responseObj.getBoolean("error"))
                {
                    JSONObject userData = responseObj.getJSONObject("data").getJSONObject("userData");
                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(userData.toString());
                    Gson gson = new Gson();
                    UserModel mUserModel = gson.fromJson(mJson, UserModel.class);

                    if (mUserModel.getUUID() == DolphPireApp.getInstance().getUUID())
                    {
                        DolphPireApp.getInstance().setUser(mUserModel);
                    }

                    if (onCompleteListener != null)
                    {
                        onCompleteListener.onComplete(mUserModel);
                    }

                } else
                {
                    JSONObject errorData = responseObj.getJSONObject("errorData");
                    if (errorData.getInt("errorType") == 100)
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

                params.put("uuid", uuid == null ? "0" : uuid);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UserDetailsAction addOnCompleteListener(ZFlowUserCallback.OnCompleteListener<UserModel> onCompleteListener)
    {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public UserDetailsAction addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UserDetailsAction addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
