package com.keshava.urlshortener.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ServerResponseCallback {
    public void onJSONObjectResponse(JSONObject jsonObject);
    public void onJSONArrayResponse(JSONArray jsonArray);
    public void onErrorResponse(Exception e);
}
