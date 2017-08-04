package com.bits.kghosh.tagit.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class JSONParser {
    public static JSONObject parseToObject(String jsonString) {
        JSONObject reader;
        try {
            reader = new JSONObject(jsonString);
            return reader;
        } catch (JSONException jse) {
            return null;
        }
    }

    public static JSONArray parseToArray(String jsonString) {
        JSONArray reader;
        try {
            reader = new JSONArray(jsonString);
            return reader;
        } catch (JSONException jse) {
            return null;
        }
    }

    public static String stringify(JSONObject object) {
        if (object != null) {
            return object.toString();
        } else {
            return null;
        }
    }
}
