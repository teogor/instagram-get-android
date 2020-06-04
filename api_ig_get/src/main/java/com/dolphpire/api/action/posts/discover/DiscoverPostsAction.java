package com.dolphpire.api.action.posts.discover;

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
import com.dolphpire.api.interfaces.ZFlowTimelineCallback;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.models.ZeoFlowPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoverPostsAction {

    //class model
    private ZFlowTimelineCallback.OnCompleteListener<ZeoFlowPost> onCompleteListener;
    private ZFlowFailureCallback.OnFailureListener onFailureListener;
    private ZFlowApiCallback.ApiKeyError mApiKeyError;

    public DiscoverPostsAction() {

    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.POSTS_DISCOVER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        JSONArray usersDataJSON = responseObj.getJSONObject("discoverPosts").getJSONArray("posts");
                        List<ZeoFlowPost> postsData = new ArrayList<>();
                        JsonParser parser = new JsonParser();
                        Gson gson = new Gson();
                        gson.newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (int i = 0; i < usersDataJSON.length(); i++) {
                            JsonElement mJson = parser.parse(usersDataJSON.get(i).toString());
                            ZeoFlowPost mZeoFlowPost = gson.fromJson(mJson, ZeoFlowPost.class);
                            postsData.add(mZeoFlowPost);
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
                        } else if (errorData.getInt("errorType") == 112) {
                            List<ZeoFlowPost> usersData = new ArrayList<>();
                            if (onCompleteListener != null) {
                                onCompleteListener.onComplete(usersData);
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
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public DiscoverPostsAction addOnCompleteListener(ZFlowTimelineCallback.OnCompleteListener<ZeoFlowPost> onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public DiscoverPostsAction addOnFailureListener(ZFlowFailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public DiscoverPostsAction addOnFailedListener(ZFlowApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
