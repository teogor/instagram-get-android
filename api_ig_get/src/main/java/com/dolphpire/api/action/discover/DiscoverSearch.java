package com.dolphpire.api.action.discover;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.ZFlowDiscoverSearchCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.models.ZeoFlowDiscoverModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoverSearch {

    //class model

    private String word;
    private ZFlowDiscoverSearchCallback.OnCompleteListener<ZeoFlowDiscoverModel> onCompleteListener;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;
    private ArrayList<Integer> queryList = new ArrayList<>();

    DiscoverSearch() {

    }

    public DiscoverSearch withWord(String word) {
        this.word = String.valueOf(word);
        return this;
    }

    public void execute() {
        DolphPireApp.getInstance().cancelPendingRequests("REQUEST_USER_SEARCH");
        // check for error flag
        //empty method
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.DISCOVER_SEARCH, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    // check for error flag
                    if (!responseObj.getBoolean("error")) {
                        if (queryList.size() != 0) {
                            queryList.remove(0);
                            if (queryList.size() == 0) {
                                JSONArray usersDataJSON = responseObj.getJSONObject("discoverData").getJSONArray("data");
                                List<ZeoFlowDiscoverModel> dataList = new ArrayList<>();
                                JsonParser parser = new JsonParser();
                                Gson gson = new Gson();
                                gson.newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
                                for (int i = 0; i < usersDataJSON.length(); i++) {
                                    JsonElement mJson = parser.parse(usersDataJSON.get(i).toString());
                                    ZeoFlowDiscoverModel mZeoFlowDiscoverModel = gson.fromJson(mJson, ZeoFlowDiscoverModel.class);
                                    dataList.add(mZeoFlowDiscoverModel);
                                }
                                if (onCompleteListener != null) {
                                    onCompleteListener.onComplete(dataList);
                                }
                            }
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
                params.put("my_uid", String.valueOf(DolphPireApp.getInstance().getUUID()));

                word = word.replaceAll(":", "<:>");
                params.put("word", word.isEmpty() ? "null" : word);
                return params;
            }
        };

        //Adding request to request queue
        strReq.setTag("REQUEST_USER_SEARCH");
        queryList.add(1);
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public DiscoverSearch addOnCompleteListener(ZFlowDiscoverSearchCallback.OnCompleteListener<ZeoFlowDiscoverModel> onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
        return this;
    }

    public DiscoverSearch addOnFailureListener(FailureCallback.OnFailureListener onFailureListener) {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public DiscoverSearch addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError) {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
