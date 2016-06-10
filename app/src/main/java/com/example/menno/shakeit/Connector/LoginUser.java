package com.example.menno.shakeit.Connector;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.menno.shakeit.Models.Globals;
import com.example.menno.shakeit.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Menno on 10-6-2016.
 */
public class LoginUser implements ApiJSONStrategy {
    JSONObject jsonObject = new JSONObject();

    public LoginUser(String userName, String password) throws JSONException {
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
        return "/login";
    }

    @Override
    public void processData(Activity activity, String result) throws JSONException {
        if (result.equals("0")){
            TextView fail = (TextView) activity.findViewById(R.id.tvLoginFail);
            fail.setVisibility(View.VISIBLE);
        } else{
            Globals.loggedin = true;
            Globals.userId = Integer.parseInt(result);
            Globals global = Globals.getInstance();
            global.notifyListener();
            activity.finish();
        }
    }
}