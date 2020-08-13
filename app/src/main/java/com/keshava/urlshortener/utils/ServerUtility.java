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

import static com.keshava.urlshortener.constants.Constants.*;

public class ServerUtility {
    private static final String TAG = "ApiUtils";

    public static void callApiPost(Context context, final String url, JSONObject jsonObject, final ServerResponseCallback serverResponseCallback) {
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
                        serverResponseCallback.onJSONObjectResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ERROR ! " + error.getMessage() );
                        serverResponseCallback.onErrorResponse(error);
                    }
                }

        );

        requestQueue.add(jsonObjectRequest);
    }

    public static void callApiGet( Context context, final String url, final ServerResponseCallback serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Log.e(TAG, "callApiGet: " + DOMAIN_URL + url );

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DOMAIN_URL + url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.toString() );
                        serverResponseCallback.onJSONArrayResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ERROR ! " + error.getMessage() );
                        serverResponseCallback.onErrorResponse(error);
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
//
//    public void handleResponse(String url, JSONObject jsonResponse ) {
//        switch (url) {
//            case SHORTEN_URL:
//                Toast.makeText(context, "Shorten URL Success!", Toast.LENGTH_SHORT).show();
//                break;
//            case GET_ALL_URL:
//                Toast.makeText(context, "URLs retrieved", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void handleArrayResponse(String url, JSONArray jsonArrayResponse ) {
//        Log.e(TAG, "handleArrayResponse: " + url );
//        switch (url) {
//            case SHORTEN_URL:
//                Toast.makeText(context, "Shorten URL Success!", Toast.LENGTH_SHORT).show();
//                break;
//            case GET_ALL_URL:
//                Toast.makeText(context, "URLs retrieved", Toast.LENGTH_SHORT).show();
//        }
//    }

}
