package com.dolphpire.api.presence;

import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowPresenceUser;
import com.dolphpire.api.links.EndPoints;

import java.util.HashMap;
import java.util.Map;

public class UserPresence {

    private int delay = 5000;
    private Runnable run;
    private Handler handler;
    private ZFlowPresenceUser.OnComplete onComplete;

    public void setActiveUser() {
        handler = new Handler();
        run = new Runnable() {
            public void run() {
                execute();
            }
        };
        handler.postDelayed(run, delay);

    }

    public void abortUserPresence() {
        if (run != null) {
            handler.removeCallbacks(run);
        }
    }

    private void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USER_SYNC_PRESENCE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                if (DolphPireApp.getInstance().getUserID() != 0) {
                    handler.postDelayed(run, delay);
                } else {
                    abortUserPresence();
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
                params.put("user_id", String.valueOf(DolphPireApp.getInstance().getUserID() == 0 ? "0" : DolphPireApp.getInstance().getUserID()));
                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);
    }

    public UserPresence addOnCompleteListener(ZFlowPresenceUser.OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

}
