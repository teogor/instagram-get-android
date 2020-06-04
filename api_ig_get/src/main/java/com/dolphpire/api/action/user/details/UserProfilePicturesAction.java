package com.dolphpire.api.action.user.details;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowApiCallback;
import com.dolphpire.api.interfaces.ZFlowFailureCallback;
import com.dolphpire.api.interfaces.ZFlowProfileImagesCallback;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.models.ZeoFlowProfileImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfilePicturesAction {

    //class model

    private String user_id;
    private ZFlowProfileImagesCallback.OnCompleteListener<ZeoFlowProfileImage> onCompleteListener;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    UserProfilePicturesAction(int user_id) {
        this.user_id = String.valueOf(user_id);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_PROFILE_PICTURES, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        JSONArray usersDataJSON = responseObj.getJSONObject("profilePictures").getJSONArray("pictures");
                        List<ZeoFlowProfileImage> postsData = new ArrayList<>();
                        JsonParser parser = new JsonParser();
                        Gson gson = new Gson();
                        gson.newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (int i = 0; i < usersDataJSON.length(); i++) {
                            JsonElement mJson = parser.parse(usersDataJSON.get(i).toString());
                            ZeoFlowProfileImage mZeoFlowProfileImage = gson.fromJson(mJson, ZeoFlowProfileImage.class);
                            postsData.add(mZeoFlowProfileImage);
                        }

                        if (onCompleteListener != null) {
                            onCompleteListener.onComplete(postsData);
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
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UserProfilePicturesAction addOnCompleteListener(ZFlowProfileImagesCallback.OnCompleteListener<ZeoFlowProfileImage> onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public UserProfilePicturesAction addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UserProfilePicturesAction addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
