package com.keshava.urlshortener.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.keshava.urlshortener.utils.Constants.*;

public class ServerUtility {
    private final String TAG = "ApiUtils";
    private Context context;

    public ServerUtility( Context context) {
        this.context = context;
    }

    public JSONObject createShortenApiJsonObject(String shortString, String expansionString) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(SHORT_STRING, shortString);
        jsonObject.put(EXPANSION_STRING, expansionString);

        return jsonObject;
    }

    public void callApiPost(final String url, JSONObject jsonObject) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Log.e(TAG, "callApiPost: " + DOMAIN_URL + url );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                DOMAIN_URL + url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "onResponse: " + response.toString() );
                        handleResponse(url, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ERROR ! " + error.getMessage() );
                    }
                }

        );

        requestQueue.add(jsonObjectRequest);
    }

    public void callApiGet(final String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Log.e(TAG, "callApiGet: " + DOMAIN_URL + url );

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                DOMAIN_URL + url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.toString() );
                        handleArrayResponse(url, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ERROR ! " + error.getMessage() );
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void handleResponse(String url, JSONObject jsonResponse ) {
        switch (url) {
            case SHORTEN_URL:
                Toast.makeText(context, "Shorten URL Success!", Toast.LENGTH_SHORT).show();
                break;
            case GET_ALL_URL:
                Toast.makeText(context, "URLs retrieved", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleArrayResponse(String url, JSONArray jsonArrayResponse ) {
        Log.e(TAG, "handleArrayResponse: " + url );
        switch (url) {
            case SHORTEN_URL:
                Toast.makeText(context, "Shorten URL Success!", Toast.LENGTH_SHORT).show();
                break;
            case GET_ALL_URL:
                Toast.makeText(context, "URLs retrieved", Toast.LENGTH_SHORT).show();
        }
    }

}
