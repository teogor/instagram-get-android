package com.dolphpire.api.action.user.details;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

public class UserFullDetailsAction
{

    private String user_id = null;
    private String username = null;
    private ZFlowUserCallback.OnCompleteListener<UserModel> onCompleteListener;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    UserFullDetailsAction(int user_id)
    {
        this.user_id = String.valueOf(user_id);
    }

    UserFullDetailsAction(String username)
    {
        this.username = username;
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

//                        if (mUserModel.getUserId() == DolphPireApp.getInstance().getUserID()) {
//                            mUserModel.setLogKey(DolphPireApp.getInstance().getUser().getLogKey());
//                            DolphPireApp.getInstance().setUser(mUserModel);
//                        }

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
                params.put("package_name", DolphPireApp.getInstance().getPackage());
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUserID()));

                params.put("user_id", user_id == null ? "null" : user_id);
                params.put("username", username == null ? "null" : username);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UserFullDetailsAction addOnCompleteListener(ZFlowUserCallback.OnCompleteListener<UserModel> onCompleteListener)
    {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public UserFullDetailsAction addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UserFullDetailsAction addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
