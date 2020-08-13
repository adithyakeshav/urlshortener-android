package com.keshava.urlshortener.utils;

import org.json.JSONException;
import org.json.JSONObject;

import static com.keshava.urlshortener.constants.Constants.EXPANSION_STRING;
import static com.keshava.urlshortener.constants.Constants.SHORT_STRING;

public class JSONUtil {
    public static JSONObject createShortenApiJsonObject(String shortString, String expansionString) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(SHORT_STRING, shortString);
        jsonObject.put(EXPANSION_STRING, expansionString);

        return jsonObject;
    }
}
