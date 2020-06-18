package com.dolphpire.api.action.orders.feed;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.api.interfaces.OnOrdersRetrieved;
import com.dolphpire.api.links.EndPoints;
import com.dolphpire.api.models.OrderModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dolphpire.api.initializer.DolphPireApp.TAG;

public class FeedOrders
{

    //class model
    private String type = "null";
    private OnOrdersRetrieved.OnCompleteListener<OrderModel> onComplete;
    private FailureCallback.OnFailureListener onFailureListener;
    private ApiCallback.ApiKeyError mApiKeyError;

    FeedOrders()
    {

    }

    void setType(String type)
    {
        this.type = type;
    }

    public void execute()
    {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.LINK_ORDERS_FEED, response ->
        {
            Log.e(TAG, "response: " + response);
            try
            {
                JSONObject responseObj = new JSONObject(response);

                // check for error flag
                if (!responseObj.getBoolean("error"))
                {
//                    JSONArray ordersJSONArray = responseObj.getJSONArray("data");
//                    List<OrderModel> mOrdersList = new ArrayList<>();
//                    JsonParser parser = new JsonParser();
//                    Gson gson = new Gson();
//                    gson.newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
//                    for (int i = 0; i < ordersJSONArray.length(); i++)
//                    {
//                        JsonElement mJson = parser.parse(ordersJSONArray.get(i).toString());
//                        OrderModel mOrderModel = gson.fromJson(mJson, OrderModel.class);
//                        mOrdersList.add(mOrderModel);
//                    }
                    if (onComplete != null)
                    {
//                        onComplete.onSuccess(mOrdersList);
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

                params.put("type", type);
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public FeedOrders addOnCompleteListener(OnOrdersRetrieved.OnCompleteListener<OrderModel> onComplete)
    {
        this.onComplete = onComplete;
        return this;
    }

    public FeedOrders addOnFailureListener(FailureCallback.OnFailureListener onFailureListener)
    {
        this.onFailureListener = onFailureListener;
        return this;
    }

    public FeedOrders addOnFailedListener(ApiCallback.ApiKeyError mApiKeyError)
    {
        this.mApiKeyError = mApiKeyError;
        return this;
    }

}
