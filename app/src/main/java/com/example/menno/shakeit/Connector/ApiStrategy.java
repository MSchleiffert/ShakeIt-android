package com.example.menno.shakeit.Connector;

import android.app.Activity;

import org.json.JSONException;

/**
 * Created by Menno on 9-6-2016.
 */
public interface ApiStrategy {
    public String getRequestType();
    public String executeStrategy();
    public void processData(Activity activity, String result) throws JSONException;
}
