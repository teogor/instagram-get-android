package com.dolphpire.api.presence;

import android.os.Handler;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.links.EndPoints;

import java.util.HashMap;
import java.util.Map;

public class NewUserUID {

    private int delay = 5000;
    private Runnable run;
    private String new_uid = "0";
    private Handler handler;

    public NewUserUID() {

    }

    public int getNewUID() {
        return Integer.parseInt(new_uid);
    }

    public void setNewUID(int new_uid_set) {
        this.new_uid = String.valueOf(new_uid_set);
        handler = new Handler();
        run = new Runnable() {
            public void run() {
                execute();
            }
        };
        handler.postDelayed(run, delay);
    }

    public void execute() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.SIGNUP_SESSION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.e(TAG, "response: " + response);
                if (DolphPireApp.getInstance().getNewUID() != 0) {
                    handler.postDelayed(run, delay);
                } else {
                    abortAction();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                //Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                //Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", DolphPireApp.getInstance().getApiKey());
                params.put("package_name", DolphPireApp.getInstance().getPackage());
                params.put("user_id", String.valueOf(DolphPireApp.getInstance().getNewUID() == 0 ? "0" : DolphPireApp.getInstance().getNewUID()));

                return params;
            }
        };

        //Adding request to request queue
        DolphPireApp.getInstance().addToRequestQueue(strReq);

    }

    public void abortAction() {
        //ZeoFlow.getInstance().abortAction();
        if (run != null) {
            handler.removeCallbacks(run);
        }
    }

}
