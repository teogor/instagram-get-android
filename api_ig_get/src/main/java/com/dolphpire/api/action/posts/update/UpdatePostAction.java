package com.dolphpire.api.action.posts.update;

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

public class UpdatePostAction {

    //class model
    private String type;
    private String post_id;
    private String description;
    private String color_1;
    private String color_2;
    private String images_url;
    private ZFlowOnCompleteCallback.OnComplete onCompleteListener;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    UpdatePostAction() {

    }

    void setPostId(int post_id) {
        this.post_id = String.valueOf(post_id);
    }

    void setPostType(int post_type) {
        this.type = String.valueOf(post_type);
    }

    void setDescription(String description) {
        this.description = String.valueOf(description);
    }

    void setColors(String color1, String color2) {
        this.color_1 = String.valueOf(color1);
        this.color_2 = String.valueOf(color2);
    }

    void setImages(String images_url) {
        this.images_url = String.valueOf(images_url);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.URL_POST_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (onCompleteListener != null) {
                            onCompleteListener.onCompleted();
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

                params.put("description", description);
                params.put("post_id", post_id);
                params.put("color_1", type.equals("0") ? "null" : color_1);
                params.put("color_2", type.equals("0") ? "null" : color_2);
                params.put("images_url", type.equals("1") ? "null" : images_url);
                params.put("type", type);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public UpdatePostAction addOnCompleteListener(ZFlowOnCompleteCallback.OnComplete onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public UpdatePostAction addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public UpdatePostAction addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
