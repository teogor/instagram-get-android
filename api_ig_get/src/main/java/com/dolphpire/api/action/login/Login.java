package com.dolphpire.api.action.login;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.interfaces.ZFlowLoginListener;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.models.ZeoFlowUser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dolphpire.api.initializer.DolphPireApp.TAG;

public class Login
{

    //class model
    private String loginKey;
    private String password;
    private ZFlowLoginListener.OnLoggedIn<ZeoFlowUser> onLoggedIn;
    private ZFlowLoginListener.OnLoginFailure onLoginFailure;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    public Login()
    {

    }

    void setLoginKey(String loginKey)
    {
        this.loginKey = loginKey;
    }

    void setPassword(String password)
    {
        this.password = password;
    }

    public void execute()
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.LOGIN, response ->
        {
            Log.e(TAG, "response: " + response);
            try
            {
                JSONObject responseObj = new JSONObject(response);
                // check for error flag
                if (!responseObj.getBoolean("error"))
                {
                    JSONObject userData = responseObj.getJSONObject("data");
                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(userData.toString());
                    Gson gson = new Gson();
                    ZeoFlowUser mZeoFlowUser = gson.fromJson(mJson, ZeoFlowUser.class);
                    DolphPireApp.getInstance().setUser(mZeoFlowUser);

                    if (onLoggedIn != null)
                    {
                        onLoggedIn.onLoggedIn(mZeoFlowUser);
                    }

                } else
                {
                    if (responseObj.getInt("errorID") == 100)
                    {
                        if (mApiKeyError != null)
                        {
                            mApiKeyError.badApi();
                        }
                    } else if (responseObj.getInt("errorID") == 102)
                    {
                        if (onLoginFailure != null)
                        {
                            onLoginFailure.onAccountClosed("Account Closed");
                        }
                    } else if (responseObj.getInt("errorID") == 103)
                    {
                        if (onLoginFailure != null)
                        {
                            onLoginFailure.onEmailNotVerified("Email Not Verified");
                        }
                    } else if (responseObj.getInt("errorID") == 124)
                    {
                        if (onLoginFailure != null)
                        {
                            onLoginFailure.onTwoStepsAuth("Two Steps Auth Enabled");
                        }
                    } else if (responseObj.getInt("errorID") == 104)
                    {
                        if (onLoginFailure != null)
                        {
                            onLoginFailure.onBadPassword("Wrong Password");
                        }
                    } else if (responseObj.getInt("errorID") == 106)
                    {
                        if (onLoginFailure != null)
                        {
                            onLoginFailure.onBadLogKey("Invalid Login Key");
                        }
                    } else
                    {
                        if (onFailureListener != null)
                        {
                            Exception exception = new Exception(responseObj.getInt("errorType") + ", " + responseObj.getInt("errorMessage"));
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
                NetworkResponse networkResponse = error.networkResponse;
//                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);

            }
        })
        {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", DolphPireApp.getInstance().getApiKey());
                params.put("secret_key", DolphPireApp.getInstance().getSecretKey());


                params.put("log_key", loginKey);
                params.put("password", password);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public Login addOnLoggedInListener(ZFlowLoginListener.OnLoggedIn<ZeoFlowUser> onLoggedIn)
    {
        this.onLoggedIn = onLoggedIn;
        return this;
    }

    public Login addOnFailureListener(ZFlowLoginListener.OnLoginFailure onLoginFailure)
    {
        this.onLoginFailure = onLoginFailure;
        return this;
    }

    public Login addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public Login addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
