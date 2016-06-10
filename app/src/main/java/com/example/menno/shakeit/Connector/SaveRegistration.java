package com.example.menno.shakeit.Connector;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Menno on 9-6-2016.
 */
public class SaveRegistration implements ApiJSONStrategy {
    JSONObject jsonObject = new JSONObject();

    public SaveRegistration(String userName, String password) throws JSONException {
        jsonObject.put("user_name", userName);
        jsonObject.put("password", password);

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
        return "/login/false";
    }

    @Override
    public void processData(Activity activity, String result) throws JSONException {

    }
}