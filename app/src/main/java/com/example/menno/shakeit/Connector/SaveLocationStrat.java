package com.example.menno.shakeit.Connector;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Menno on 9-6-2016.
 */
public class SaveLocationStrat implements ApiJSONStrategy {
    JSONObject jsonObject = new JSONObject();

    public SaveLocationStrat(String locationName, String longitude, String latitude) throws JSONException {
        jsonObject.put("location_name", locationName);
        jsonObject.put("longitude", longitude);
        jsonObject.put("latitude", latitude);
    }

    @Override
    public String stringJsonObject() {
        return jsonObject.toString();
    }

    @Override
    public String getRequestType() {
        return "json";
    }

    @Override
    public String executeStrategy() {
        return "/saveLocation";
    }

    @Override
    public void processData(Activity activity, String result) throws JSONException {

    }
}
