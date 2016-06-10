package com.example.menno.shakeit.Connector;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.menno.shakeit.Models.Location;
import com.example.menno.shakeit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Menno on 10-6-2016.
 */
public class GetLocationList implements ApiStrategy {
    String requestType = "simple";
    int userId = 0;

    public GetLocationList(int userId){
        this.userId = userId;
    }

    @Override
    public String getRequestType() {
        return requestType;
    }

    @Override
    public String executeStrategy() {
        return "/list/all/" + userId;
    }

    @Override
    public void processData(Activity activity, String result) throws JSONException {
        List<Location> locationList = new ArrayList<>();
        try {

            JSONObject jsonobj = new JSONObject(result);
            JSONArray jsonarr = jsonobj.getJSONArray("list");

            for (int i = 0; i < jsonarr.length(); i++) {
                jsonobj = (JSONObject) jsonarr.get(i);
                Location event = new Location(jsonobj.getInt("location_id"), jsonobj.getDouble("longitude"),jsonobj.getDouble("latitude"), jsonobj.getString("city"));
                locationList.add(event);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Context context = activity;
        ListView lv = (ListView) activity.findViewById(R.id.locationList);

    }
}
